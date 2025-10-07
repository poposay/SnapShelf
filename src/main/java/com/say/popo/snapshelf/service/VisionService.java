package com.say.popo.snapshelf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotationContext;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service
public class VisionService {
	
	private final ImageAnnotatorClient imageAnnotatorClient;
	
	public VisionService(ImageAnnotatorClient imageAnnotatorClient) {
		this.imageAnnotatorClient = imageAnnotatorClient;
	}

    public List<String> extractLabels(byte[] imageBytes)  throws IOException {
            
    	List<AnnotateImageRequest> requests = new ArrayList<>();
    	
    	ByteString imgBytes = ByteString.copyFrom(imageBytes);
    	
    	Image img = Image.newBuilder().setContent(imgBytes).build();
    	Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
    	
    	AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
    			.addFeatures(feat)
    			.setImage(img)
    			.build();
    	
    	requests.add(request);
    	
    	List<String> tags = new ArrayList<>();
    	
    	AnnotateImageResponse response = imageAnnotatorClient
    	        .batchAnnotateImages(requests)
    	        .getResponses(0);
    		
    		if (response.hasError()) {
    			System.out.println("Error:" + response.getError().getMessage());
    			return tags;
    		}
    		
    		for (EntityAnnotation annotation : response.getLabelAnnotationsList()) {
    			tags.add(annotation.getDescription());
    		}
    		
    		return tags;
    	}
    	 /* 
            //仮のタグをセット
            tags.add("海");
            tags.add("夕日");
            tags.add("子犬");
            
           

            // タイムアウト付き設定を準備する
            GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream("C:/Users/saypo/Documents/popoalbum/src/main/resources/keys/vision-key.json"))
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

            ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
            	    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
            	    .setTransportChannelProvider(
            	        HttpJsonTransportChannel.newBuilder()
            	            .setEndpoint("vision.googleapis.com:443")
            	            .build()
            	    )
            	    .build();

            // クライアント作成
            try (ImageAnnotatorClient client = ImageAnnotatorClient.create(settings)) {
                // リクエスト作成
                ImageSource imgSource = ImageSource.newBuilder().setImageUri(imageUrl).build();
                Image image = Image.newBuilder().setSource(imgSource).build();
                Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feature).setImage(image).build();

                BatchAnnotateImagesResponse response = client.batchAnnotateImages(List.of(request));
                List<EntityAnnotation> annotations = response.getResponses(0).getLabelAnnotationsList();

                // 🌼 タグ抽出
                return annotations.stream()
                    .map(EntityAnnotation::getDescription)
                    .collect(Collectors.toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("タグ抽出に失敗しました");
        }
    }
}

@Service
public class VisionService {

    public List<String> analyzeImageByUrl(String fullUrl) {
        List<String> tags = new ArrayList<>();

        // 🐾 デバッグ用ログ
        System.out.println("画像解析スタート🐶");
        System.out.println("解析対象の画像URL：" +  fullUrl);

        try (InputStream input = new URL(fullUrl).openStream()) {
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

     {
    	 return tags;
     }
      */ 
    
}