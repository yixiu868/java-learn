package com.ww.weak;

import java.lang.ref.WeakReference;

public class Salad extends WeakReference<Apple> {

    public Salad(Apple referent) {
        super(referent);
    }

}
