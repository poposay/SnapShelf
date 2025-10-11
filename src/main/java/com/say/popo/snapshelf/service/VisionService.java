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

}