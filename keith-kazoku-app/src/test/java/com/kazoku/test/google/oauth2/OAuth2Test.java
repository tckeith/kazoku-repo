package com.kazoku.test.google.oauth2;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/kazoku.xml")
@WebAppConfiguration
public class OAuth2Test {
	
	private final String json = "C:\\Users\\EnkeiRaijin\\workspace\\kazoku-repo\\keith-kazoku-config\\Kazoku-2b31a38cb75a.json";
	
	
	@Test
	public void apiCallTest(){
		
		try {
			GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(json))
				    .createScoped(Collections.singleton(DriveScopes.DRIVE));
			
			JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			
			Drive drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential).build();
			
			About getinfo = drive.about().get().setFields("user, storageQuota").execute();
						
			System.out.println(getinfo.toPrettyString());
			
			String pageToken = null;
			do {
			    FileList result = drive.files().list()
			            //.setQ("mimeType='image/jpeg'")
			            //.setSpaces("drive")
			    		.setPageSize(10)
			            .setFields("nextPageToken, files(id, name)")
			            .setPageToken(pageToken)
			            .execute();
			    
			    System.out.println("FILE size: " + result.getFiles().size());
			    for(File file: result.getFiles()) {
			        System.out.printf("Found file: %s (%s)\n",
			                file.getName(), file.getId());
			    }
			    pageToken = result.getNextPageToken();
			} while (pageToken != null);
			

		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
