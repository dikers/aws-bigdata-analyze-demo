//snippet-sourcedescription:[S3AsyncOps.java demonstrates how to use the S3 asynchronous client.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[s3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
/*
 * Copyright 2011-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nwcd.gallery.s3;
// snippet-start:[s3.java.async_ops.complete]
// snippet-start:[s3.java.async_ops.import]

import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

// snippet-end:[s3.java.async_ops.import]
// snippet-start:[s3.java.async_ops.main]
public class S3AsyncOps {

    private static final String BUCKET = "dikers.nwcd";
	private static final String KEY = "key";

	public static void main(String[] args) {

        Region region = Region.US_EAST_1;
    	S3AsyncClient client = S3AsyncClient.builder().region(region).build();
        CompletableFuture<PutObjectResponse> future = client.putObject(
                PutObjectRequest.builder()
                                .bucket(BUCKET)
                                .key(KEY)
                                .build(),
                AsyncRequestBody.fromFile(Paths.get("/Users/mac/d/tmp/testfile.in"))
        );
        future.whenComplete((resp, err) -> {
            try {
                if (resp != null) {
                    System.out.println("my response: " + resp);
                } else {
                    // Handle error
                    err.printStackTrace();
                }
            } finally {
                // Lets the application shut down. Only close the client when you are completely done with it.
                client.close();
            }
        });

        future.join();
    }
}
 
// snippet-end:[s3.java.async_ops.main]
// snippet-end:[s3.java.async_ops.complete]
