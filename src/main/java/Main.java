/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.util.Charsets;
import com.google.common.io.ByteSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

public class Main {

  public static void main(String[] args) throws IOException {
    // Oauth Scope list here: https://developers.google.com/identity/protocols/googlescopes
    Collection<String> scopes = Arrays.asList(
        "https://www.googleapis.com/auth/userinfo.email",
        "https://www.googleapis.com/auth/userinfo.profile");

    // Generate a Oauth Credential Object from the service account
    GoogleCredential cred = GoogleCredential.fromStream(new FileInputStream(
        "./client_secret.json"))
        .createScoped(scopes);

    // Ask google for an Access Token
    cred.refreshToken();

    // Print the Access Token for debugging Purposes
    System.out.println("Access Token: " + cred.getAccessToken());

    // Find out what information we can get on access token.
    System.out.println(accessTokenInfo(cred.getAccessToken()));
  }

  /**
   * Gives you Access Token's Account Information as JSON (Email, Id, Name, Picture,...)
   * @param accessToken a google oauth AccessToken
   * @return JSON UTF8 encoded String
   * @throws IOException if non 200 HTTP Status given
   */
  public static String accessTokenInfo(String accessToken) throws IOException {
    // Make Get request to Google Plus API for info on access token.
    URL url = new URL("https://www.googleapis.com/plus/v1/people/me?access_token=" + accessToken);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setConnectTimeout(5000);
    con.setReadTimeout(5000);
    con.setInstanceFollowRedirects(false);

    if (con.getResponseCode() != 200) {
      throw new IOException("HTTP " + con.getResponseCode() + ": " + con.getResponseMessage());
    }

    ByteSource b = new ByteSource() {
      @Override
      public InputStream openStream() throws IOException {
        return con.getInputStream();
      }
    };
    return ByteSource.concat(b).asCharSource(Charsets.UTF_8).read();
  }

}
