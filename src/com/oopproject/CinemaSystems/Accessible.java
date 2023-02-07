package com.oopproject.CinemaSystems;

/*
    This interface is responsible for checking accessibility
    of product at this moment checking it with cinema database
 */

public interface Accessible {

    boolean isAccessible(String name);
}
