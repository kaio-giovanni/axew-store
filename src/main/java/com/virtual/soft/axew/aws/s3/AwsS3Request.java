package com.virtual.soft.axew.aws.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.virtual.soft.axew.aws.AwsCredentialsProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AwsS3Request {
    private final AmazonS3Client s3Client;
    private final String bucket;

    public AwsS3Request(String bucket, String region) {
        this.bucket = bucket;
        this.s3Client = (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AwsCredentialsProvider())
                .withRegion(region)
                .build();
    }

    public List<S3ObjectSummary> listAndFilterByExtension(String s3FolderPath, String fileExtension) {
        ListObjectsV2Result result = list(s3FolderPath, 3000);
        List<S3ObjectSummary> bucketFiles = new ArrayList<>();

        do {
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            bucketFiles.addAll(objects
                    .stream()
                    .filter(object -> object.getKey().endsWith(fileExtension))
                    .toList());

            result = list(s3FolderPath, result);
        } while (result.isTruncated());

        return bucketFiles;
    }

    public ListObjectsV2Result list(String s3FolderPath, int size) {
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withMaxKeys(size)
                .withPrefix(s3FolderPath);

        return s3Client.listObjectsV2(request);
    }

    public ListObjectsV2Result list(String s3FolderPath, ListObjectsV2Result listObjectsV2Result) {
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withMaxKeys(3000)
                .withContinuationToken(listObjectsV2Result.getNextContinuationToken())
                .withPrefix(s3FolderPath);

        return s3Client.listObjectsV2(request);
    }

    public PutObjectResult upload(String s3FilePath, File localFile) {
        return s3Client.putObject(bucket, s3FilePath, localFile);
    }

}
