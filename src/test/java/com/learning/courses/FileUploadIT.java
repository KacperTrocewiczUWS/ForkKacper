package com.learning.courses;

import com.learning.courses.AbstractIntegrationTest;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
public class FileUploadIT extends AbstractIntegrationTest {
    private static final String RESOURCE_DIR = "src/test/resources/files/";
    private static final String UPLOAD_ENDPOINT = "/api/upload";

    @LocalServerPort
    protected int port;

    protected HttpHost getHttpHost() {
        return HttpHost.create("http://localhost:%d".formatted(port));
    }

    @Test
    public void AllValidAttributes() throws Exception {
        File file = new File(RESOURCE_DIR + "AllValid.jpg");
        final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
        initRequestWithFile(file, httpPost);

        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpPost);

            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }

    //errors
    @Test
    public void AllAttributesInvalid() throws Exception {
        File file = new File(RESOURCE_DIR + "AllInvalid.jpeg");
        final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
        initRequestWithFile(file, httpPost);

        try (var client = HttpClients.createDefault()) {
            var response = client.execute(getHttpHost(), httpPost);


            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

            //zawartość odpowiedzi
            var bytes = response.getEntity().getContent().readAllBytes();
            assertThat(bytes).isNotEmpty();
            var responseString = new String(bytes);
            var jsonObject = new JSONObject(responseString);

            assertThat(jsonObject.getString("message")).isEqualTo("All attributes are invalid");

        }
    }
        //only 1 error
        @Test
        public void OnlyResolutionAttributeInvalid () throws Exception {
            File file = new File(RESOURCE_DIR + "ResInvalid.jpg");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);

            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attribute resolution is invalid");
            }
        }

        @Test
        public void OnlyExtensionAttributeInvalid () throws Exception {
            File file = new File(RESOURCE_DIR + "ExInvalid.jpeg");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);

            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attribute extension is invalid");
            }
        }

        @Test
        public void OnlySizeAttributeInvalid () throws Exception {

            File file = new File(RESOURCE_DIR + "SizeInvalid.jpg");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);

            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attribute size is invalid");
            }
        }

        //2 errors
        @Test
        public void ResolutionAndExtensionAttributesInvalid () throws Exception {
            File file = new File(RESOURCE_DIR + "ResExInvalid.png");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);

            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attributes resolution and extension are invalid");
            }

        }

        @Test
        public void ResolutionAndSizeAttributesInvalid () throws Exception {
            File file = new File(RESOURCE_DIR + "ResSizeInvalid.jpg");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);


            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attributes size and resolution are invalid");
            }
        }

        @Test
       public void ExtensionAndSizeAttributesInvalid () throws Exception {
            File file = new File(RESOURCE_DIR + "SizeExInvalid.png");
            final HttpPost httpPost = new HttpPost(UPLOAD_ENDPOINT);
            initRequestWithFile(file, httpPost);

            try (var client = HttpClients.createDefault()) {
                var response = client.execute(getHttpHost(), httpPost);


                assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

                var bytes = response.getEntity().getContent().readAllBytes();
                assertThat(bytes).isNotEmpty();
                var responseString = new String(bytes);
                var jsonObject = new JSONObject(responseString);

                assertThat(jsonObject.getString("message")).isEqualTo("Attributes size and extension are invalid");
            }
        }
    }



