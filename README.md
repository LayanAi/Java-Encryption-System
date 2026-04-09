# Java Encryption System

A simple java project that encrypts and decrypts sentences using substitution keys.

The system allows the user to create up to three keys , store secure sentences , and apply encryption  and decryption based on the selected key.

The program also includes a PIN system for security before modifying or displaying keys.

## Features

- Set a secure four‑digit PIN.
- Create or update up to three encryption keys.
- Validate keys to ensure no repeated characters, equal length, and correct mapping.
- Store two SecureSentence objects.
- Enter plain or already encrypted sentences.
- Encrypt a sentence using a selected key.
- Decrypt a sentence using the key used for encryption.
- Display keys and sentences.
- Use a menu‑driven console interface.

## Classes Overview:

### Key

Handles storing ORIGINAL and CODE strings, validating key format, mapping characters for encryption and decryption, and displaying key details. 

### SecureSentence

Handles storing plain or encrypted sentences, encrypting using a key, decrypting using the same key, and displaying the sentence status and key used.

### Main

Handles PIN setup and validation, the menu system, selecting keys and sentences, calling encryption and decryption functions, and displaying all objects.

## How Encryption Works:

Each key contains two strings: ORIGINAL (characters before encryption) and CODE (characters after encryption).
When encrypting, if a character exists in ORIGINAL, it is replaced with the matching character in CODE.
If the character does not exist in the key, it remains unchanged.
Decryption reverses the process using the same key.

## Example Usage

Setting a key involves entering the ORIGINAL and CODE strings. If the key is valid, it is accepted.
Encrypting a sentence such as “cab” using a suitable key might produce “zyx,” and decrypting it returns the sentence to “cab.”

## Purpose

This project is designed for practicing :

- Java basics
- Object‑oriented programming
- Input validation
- Character‑based encryption logic
- Working with multiple objects
