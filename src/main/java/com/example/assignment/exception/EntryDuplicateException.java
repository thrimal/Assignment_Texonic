/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.assignment.exception;

public class EntryDuplicateException extends RuntimeException {
    public EntryDuplicateException(String message) {
        super(message);
    }
}
