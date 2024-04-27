// UserManager.aidl
package com.example.binderdemo.aidl;

// Declare any non-default types here with import statements

import com.example.binderdemo.model.User;

interface UserManager {

    void addUser(inout User user);

    User getUser(int index);
}