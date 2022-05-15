package com.future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeFlow {

    public static void main(String[] args) {

        final SimpleCell c3 = new SimpleCell("C3");
        final SimpleCell c2 = new SimpleCell("C2");
        final SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);
        c1.onNext(10);
        c2.onNext(20);

        System.out.println("======================================");

        final ArithmeticCell c6 = new ArithmeticCell("C6");
        final SimpleCell c5 = new SimpleCell("C5");
        final SimpleCell c4 = new SimpleCell("C4");

        c4.subscribe(c6::setLeft);
        c5.subscribe(c6::setRight);

        c4.onNext(10);
        c5.onNext(20);
        c4.onNext(15);
    }

    public static class Quote {

      private final String shopName;
      private final double price;
      private final Discount.Code discountCode;

      public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
      }

      public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
      }

      public String getShopName() {
        return shopName;
      }

      public double getPrice() {
        return price;
      }

      public Discount.Code getDiscountCode() {
        return discountCode;
      }

    }
}
