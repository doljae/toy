package com.example.test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUUID {

    public static void main(String[] args) {
//        Set<UUID> set = new HashSet<>();
//        for (int i = 0; i < 100000000; i++) {
//            final String testString = Integer.toString(i);
//            final UUID uuid = UUID.nameUUIDFromBytes(testString.getBytes(StandardCharsets.UTF_8));
////            log.info(String.valueOf(uuid.variant()));
////            log.info(String.valueOf(uuid.version()));
//            log.info(uuid.toString());
//            if (set.contains(uuid)) {
//                log.info(testString);
//                break;
//            }
//            set.add(uuid);
//        }
//        7d6a60e9-0eae-32fb-94e6-163f989b156e
//        7d6a60e9-0eae-32fb-94e6-163f989b156e
//        System.out.println(
//            "12345678900000002342342342342344356456400000000000000466564645645645645623423423423422".getBytes(
//                StandardCharsets.UTF_8));
//        System.out.println(UUID.nameUUIDFromBytes(
//            "1234567890000000023423423423423443564564000000000000000466564645645645645623423423423422".getBytes(
//                StandardCharsets.UTF_8)));
//
//        for (int i = 0; i < 1000000; i++) {
//            final String s = Integer.toString(i);
//            log.info(s.getBytes(StandardCharsets.UTF_8).toString());
//        }

        for (int pk = 1; pk < 10000000; pk++) {
            // pk is positive integer, convert to String
            final String stringPk = Integer.toString(pk);
            // convert to byte array
            final byte[] convertedPk = stringPk.getBytes(StandardCharsets.UTF_8);
            // convert to byte array using MD5, generate UUID
            final UUID uuid = UUID.nameUUIDFromBytes(convertedPk);
            // if byte array which is generated from MD5 return same value,
            // Duplicated UUID will be generated

        }
    }
}
