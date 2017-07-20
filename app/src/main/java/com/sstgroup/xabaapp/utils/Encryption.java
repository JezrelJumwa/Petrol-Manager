package com.sstgroup.xabaapp.utils;


import android.util.Base64;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Encryption {

    //commenting this because comments are not put in class file binary and while decompilation they will not be there
    //key for staging and dev
//    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg5MB9aB4OzwqrsW8TdeVfNwdqVf+XUwJPDL3qO2kYUFSu95QeZlOlslyulHsqoyTTn03MnnSi03ixwpZ9Uxv1lszXQj4ozZCBHEJsutQyYZxU0vWoIaZn13G1ro0BxQRD5fQ5/wUVNjt9H8bUv5KY3ahVlwSEKMM9+JqgcWwJ+ZICwfUrpqKklR+7W3cdDGuK3Jl69NViUO+VgJYEHAyu8q9mBhKKGN28Txdu2MuCO30LJsUdBwF+P18Fqr7banMfXjnRpkpOPFKQ0iPWfceyUvDXyaxA5pytaijzkfYF1Lak22RRyR0jFWJqfd3ccVXsId/6iu/3ayKcuc4yasnlwIDAQAB";
//    private static final String PRIVATE_KEY = "MIIEowIBAAKCAQEAg5MB9aB4OzwqrsW8TdeVfNwdqVf+XUwJPDL3qO2kYUFSu95QeZlOlslyulHsqoyTTn03MnnSi03ixwpZ9Uxv1lszXQj4ozZCBHEJsutQyYZxU0vWoIaZn13G1ro0BxQRD5fQ5/wUVNjt9H8bUv5KY3ahVlwSEKMM9+JqgcWwJ+ZICwfUrpqKklR+7W3cdDGuK3Jl69NViUO+VgJYEHAyu8q9mBhKKGN28Txdu2MuCO30LJsUdBwF+P18Fqr7banMfXjnRpkpOPFKQ0iPWfceyUvDXyaxA5pytaijzkfYF1Lak22RRyR0jFWJqfd3ccVXsId/6iu/3ayKcuc4yasnlwIDAQABAoIBADZSDiALhTfnajPHvStiEvx+n9xXUNPR3Yo1/JuIqTHh1zdq3Eynp5OHTc9wnUX4TaS8+3niY7NRj4vfUS59xTp5YqZJWuV/NBhDVt7AWhUpZUYfbFfP2kK6Y6opK+u0NCr84am1H+u7t9oWVACarbmMs6VE08x8lJEOsCwtosGFrin6/POi9NbRelJvVto+7LSFnXlEyQcmhf00G38ppDF/cE3F9JteVoohtP2Ii7I9/GayCs1gHOK98sWoUjYkxAOWCQ853ycNOy9CTNi1tApC84P5/kNoP9kn2MT70Jow97Aa7HkozCvGGPU84i4pBeR6NWpaub5i/HJCEVTbzDkCgYEA3FUnF6+E5YM3jdNLMbtTwfEQ98a5f9ECeHx6u1VhlRT3TWZ09Ge+jwRBbQVkuqvYSuAOFKfSLnTi/7XVRm4Bsw3BCRCwFsXlBZpm+lowqCh7YdqmGsiqx24eZKQgQEWvunlyQnSWRITtNJIcrQQ4X5+CgP0fOr7/Fg/zvMa3LN0CgYEAmN+aCHcHaP/ghPBgIrUactBS0lzARPgAWytJJAcWpTDwZHDRy/1qe2XUaW/+HghD5MSCT/1ij/NYDnVMlSgCm009cuyQw6UuSh+xtxuWPY6D+M7KCzCIfSkFJJkuDyW5xwLhXlHUPMvrprR49G1ZnIf39+QH35vO/OA8S2r9lQMCgYEAgD7ILE8raXPA6DouMFo63E32dGIlIrKDX6IExflifSXg9BpIxOGF1zTA6DYVdVoE4UAcsgUkn4nFftVVuFLbhl7hFuextu+k6GgAvIO2gopdlo1v1XMrhD0iT64AX6iwKjf6O5gkxukgMFNDgalNLstnaLDsdoMpcqwCUMTRZbkCgYAO3tlV73YqEZHefJ9tYN6Ewpz714fgbZtjQu+ncIUUZpFLw+m0hR8hh8NEyl8WvskT7MWcUFKRHCO5rFFZxP2T68yJbFecoGnDThf0btuLe1x9DGh89CiivCXC7J+dW5cZs78GdMFHdLKkkbOeWzC3WEaXt3fImZhl4s4aq/qkzwKBgAf5+MMQnrYTrUrFpm9O6sAn77aqiMAjhJBTGqo6UKVlXnSESzDLcfiG+rNVlpJlcN/LOIBdinlPJJM5W34TPgZMHD/w8PbupoYJx93SCfTyrF5cJ+xQ8620e9HoWLMGhOfvr2qajpMwfqutvQe4O3IcQZadlwASZtLVz87a0p8B";

//    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtAOaObKmPtf3T7vSGuLHww35hiJyT1MXjwOfocCY1AHui66UTzoxaJ7hD+oBTVJJhaW8inBPibAtFYJJcgiMi9ZM2JnP12CJ5D4p63yg3n2KIel8zQulD2m2eidZKKhMOoxLC5Ykp3cb7z2BoaowrGVuX1XSVU7SQhiIl+oLXGcgCo6mkUFDlOQOKsZUap19oUPmiIs6Gcsq89f9GSS7TOSF3shpZnO7K/jZDJHO0D2UyXXz+YTacg6thqOJpONOVJfmWA0p69HFtYDLZNjcBSp2e6GNJXpn1V2JjrZW9NsAnQ1eP/bMnSTKbqzH/vVSAwMBlPQpwgA/Ujm5KHEiZQIDAQAB";

    public static String encryptionRSA(final String text) {

        byte[] encryptedText = new byte[0];

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
            encryptedText = cipher.doFinal(text.getBytes());
//            Timber.d("decryptionRSA= " + decryptionRSA(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(encryptedText, Base64.DEFAULT), Charset.defaultCharset());
    }

    private static PublicKey getPublicKey() {

        PublicKey publicKey = null;

        try {
            byte[] byteKey = Base64.decode(Constants.PUBLIC_KEY.getBytes(), Base64.DEFAULT);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = kf.generatePublic(X509publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;
    }

//    public static String decryptionRSA(final byte[] encryptedBytes) {
//
//        String decryptedText = "";
//
//        try {
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
//            decryptedText = new String(cipher.doFinal(encryptedBytes));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return decryptedText;
//    }
//
//    private static PrivateKey getPrivateKey() {
//
//        PrivateKey privateKey = null;
//
//        try {
//            byte[] byteKey = Base64.decode(PRIVATE_KEY.getBytes(), Base64.DEFAULT);
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
//            KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
//            privateKey = kf.generatePrivate(pkcs8EncodedKeySpec);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return privateKey;
//    }
}
