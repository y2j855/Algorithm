package com.tony.basis.class02;

import com.tony.basis.GenericsUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/8 11:26
 * Description:
 */
class StackTest {

    Stack<Integer> stack;

    @BeforeEach
    public void init(){
        stack = new Stack<>(Integer.class);
        stack.push(1);
    }

    @Test
    void push() {
        stack.push(2);
        stack.push(3);
        assertEquals(3,stack.index);
    }

    @Test
    void pop() {
        stack.push(2);
        stack.push(3);
        Integer number = stack.pop();
        assertEquals(3,number);
        assertEquals(2,stack.index);
    }

    @Test
    void peek() {
        assertEquals(1,stack.peek());
        assertEquals(1,stack.index);
    }

    @AfterEach
    public void finish(){
        stack = null;
    }
}