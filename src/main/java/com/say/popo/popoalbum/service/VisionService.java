package com.say.popo.popoalbum.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service
public class VisionService {

    public List<String> analyzeImageByUrl(String imageUrl) {
        List<String> tags = new ArrayList<>();

        // 🐾 デバッグ用ログ
        System.out.println("画像解析スタート🐶");

        try (InputStream input = new URL(imageUrl).openStream()) {
            ByteString imgBytes = ByteString.copyFrom(input.readAllBytes());

            Image img = Image.newBuilder()
                    .setContent(imgBytes)
                    .build();

            Feature feature = Feature.newBuilder()
                    .setType(Feature.Type.LABEL_DETECTION)
                    .build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feature)
                    .setImage(img)
                    .build();

            try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
                BatchAnnotateImagesResponse response = vision.batchAnnotateImages(List.of(request));

                List<AnnotateImageResponse> responses = response.getResponsesList();
                AnnotateImageResponse firstResponse = responses.get(0);
                List<EntityAnnotation> labels = firstResponse.getLabelAnnotationsList();

                for (EntityAnnotation label : labels) {
                    tags.add(label.getDescription());
                }

                // 🐾 デバッグ用ログ（1回だけ）
                System.out.println("取得したタグ：" + tags);
            }

        } catch (IOException e) {
            System.err.println("画像読み込みまたはVision APIエラー");
            e.printStackTrace();
        }

        return tags;
    }
}