package com.company;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class SevedCros implements Serializable {
        private int[][] numberHeight ;
        private int[][] numberWidth ;
        private int[][][] colorHeight;
        private int[][][] colorWidth ;
        private  transient String numberHeight_MD5;
        private transient String numberWidth_MD5;
        private transient String colorHeight_MD5;
        private transient String colorWidth_MD5;

        public SevedCros(int[][] namberWidth, int[][] namberHeight, int[][][] colorHeight,
                         int[][][] colorWidth){
                this.numberHeight = namberHeight;
                this.colorWidth = colorWidth;
                this.colorHeight = colorHeight;
                this.numberWidth = namberWidth;
        }

        public int[][] getNamberHeight() {
                return numberHeight;
        }

        public int[][] getNamberWidth() {
                return numberWidth;
        }

        public int[][][] getColorHeight() {
                return colorHeight;
        }

        public int[][][] getColorWidth() {
                return colorWidth;
        }

        public String getColorHeight_MD5() {
                return colorHeight_MD5;
        }

        public String getColorWidth_MD5() {
                return colorWidth_MD5;
        }

        public String getNumberHeight_MD5() {
                return numberHeight_MD5;
        }

        public String getNumberWidth_MD5() {
                return numberWidth_MD5;
        }

        public String toString(){
                numberHeight_MD5 = (String) getHash(Arrays.deepToString(numberHeight).getBytes());
                numberWidth_MD5 = (String) getHash(Arrays.deepToString(numberWidth).getBytes());
                colorHeight_MD5 = (String) getHash(Arrays.deepToString(colorHeight).getBytes());
                colorWidth_MD5 = (String) getHash(Arrays.deepToString(colorWidth).getBytes());

                return "Numbers for width :" + numberWidth_MD5 + "\n" +"Numbers for height :"+
                        numberHeight_MD5 + "\n" + "Colors for widht :" + colorWidth_MD5 + "\n" +
                        "Colors for height :" + colorHeight_MD5;
        }

        private static String getHash(byte[] inputBytes){
                String hashValue = "";
                MessageDigest messageDigest = null;
                try {
                        messageDigest = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                }
                messageDigest.update(inputBytes);
                byte[] digestedBytes = messageDigest.digest();
                hashValue = Base64.getEncoder().encodeToString(digestedBytes);
                return hashValue;
        }
        }
