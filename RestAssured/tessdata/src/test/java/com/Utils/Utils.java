package com.Utils;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;

public class Utils {

        public static String decodePassword(String Password){
            byte[] decodedString = Base64.decodeBase64(Password);
            return (new String(decodedString));
        }

}
