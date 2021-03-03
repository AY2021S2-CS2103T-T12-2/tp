---
layout: page
title: User Guide
---

## Introduction

DieTrack is a **desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight.** If you can type fast, you’ll be able to record your meals in this app much faster than other traditional GUI-based diet tracking apps.

## Table of Contents

<!--ts-->
* [Introduction](#introduction)
* [Quick start](#quick-start)
* [Features](#features)
* [Body Mass Index (BMI) Tracker](#1-body-mass-index-bmi-tracker)
  * [Input weight, height and ideal weight](#11-input-weight-height-and-ideal-weight)
  * [Query weight, height and BMI](#12-query-weight-height-and-bmi)
  * [Update weight, height and ideal weight](#13-update-weight-height-and-ideal-weight)
* [Diet Plan Selector](#2-diet-plan-selector)
  * [Get diet recommendations based on current BMI](#21-get-diet-recommendation-based-on-current-bmi)
  * [View active diet plan](#22-view-active-diet-plan)
  * [Select active diet plan](#23-select-active-diet-plan)
* [Macronutrients Tracker](#3-macronutrients-tracker)
  * [Input carbohydrates, fats and protein intake](#31-input-carbohydrates-fats-and-protein-intake)
  * [List food intake for certain days](#32-list-food-intake-for-certain-days)
* [Command summary](#command-summary)
<!--te-->

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dietrack.jar`.

1. Copy the file to the folder you will be launching DieTrack from on a daily basis.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`bmi`**`h/150 w/70 i/75`: Initializes the current user BMI with height of 150cm, weight of 70kg and ideal weight of 75kg.

   * **`bmi`**`query`: Queries the current user's BMI with its height and weight information.

   * **`bmi`**`update h/170 w/70 i/80`: Updates the current user BMI to height of 170cm, weight of 70kg and ideal weight of 80kg.

   * **`plan`**`recommended`: Queries the recommended diet plan based on user's BMI.

   * **`plan`**`t/1`: Queries more information of the weight loss diet plan.

   * **`food`**`d/today n/tomatoes p/10 c/10 f/10`: Adds tomato consumption with protein of 10g, carbohydrates of 10g and fats of 10g to the food intake for today.

   * **`food`**`query d/today`: Queries the food intake for today.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features


--------------------------------------------------------------------------------------------------------------------

## 1. Body Mass Index (BMI) Tracker

### 1.1 Input weight, height and ideal weight

On application's first launch, the user is prompted to enter his/her height, weight and ideal weight.

**Format:** `bmi h/height(cm) w/weight(kg) i/ideal_weight(kg)`

**Example:** `bmi h/170 w/70 i/80`

**Expected output:**

BMI (h: 170cm, w: 70kg, i: 80kg) has been saved.

### 1.2 Query weight, height and BMI

The user can query their weight and height everyday to see their progress.

**Format:** `bmi query`

**Expected output:**

Below is your current height and weight:\
Last Updated: 2021-02-28\
Weight: 100kg\
Height: 170cm\
BMI: 34.6 (High Risk)

### 1.3 Update weight, height and ideal weight

The user can query their weight and height everyday to see what they have input. 
This is recommended, so the user will have a more accurate view of their BMI status.

**Format:** `bmi update h/height(cm) w/weight(kg) i/ideal_weight(kg)`

**Example:** `bmi update h/170 w/70 i/80`

**Expected output:**

BMI (h: 170cm, w: 70kg, i: 80kg) updated for current user.

## 2. Diet Plan Selector

### 2.1 Get diet recommendation based on current BMI

Gets a recommended diet plan based on the user's current BMI stored in the system.

**Format:** `plan recommended`

**Expected output:**

Here is the recommended diet plan for your BMI of 29.4:
1) Weight Loss Diet (Less-carbs)

### 2.2 View active diet plan

Shows the current active diet plan previously selected by the user.

**Format:** `plan current`

**Expected output:**

You are on the Weight Loss Diet (Less-carbs)!\
Here's the information tailored to your BMI:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

### 2.3 Select active diet plan

Changes the current active diet plan to the newly specified plan.

**Format:** `start p/plan`

**Example:** `start p/1`

**Expected output:**

You are now on the Weight Loss Diet (Less-carbs)!\
Here's the information tailored to your BMI:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

### 2.4 List all available diet plans

Displays a list of available diet plans.

**Format:** `plans`

**Expected output:**

Here are the available diet plans:
1) Weight Loss Diet
2) Weight Gain Diet
3) Muscle Gain Diet

### 2.5 View information about a particular diet plan

Displays information about a particular diet plan.

**Format:** `plan \tID`

**Example:** `plan t\1`

**Expected output:**

Here's more information about the Weight Loss plan:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

## 3. Macronutrients Tracker

### 3.1 Input carbohydrates, fats and protein intake

For daily meals, the user is required to enter his/her current intake of macronutrients, in terms of carbohydrates, fats, and proteins. The metric unit of measurement specified is in grams, delimited by commas.


**Format:** `food d/DATE n/NAME p/PROTEINS c/CARBOHYDRATES f/FATS`

**Example:** `food d/today n/tomatoes p/10 c/10 f/10`\
`food d/today n/onions p/20 c/0 f/100`

**Expected output:**

Tomatoes (Protein: 10g, Carbohydrates: 10g, Fats:10g) has been tracked. 170 kcal consumed.\
Onions (Protein: 20g, Carbohydrates: 0g, Fats:100g) has been tracked. 340 kcal consumed.

### 3.2 List food intake for certain days

The user will be able to see the current daily food intake and how far off, or if they have exceeded their daily calorie intake requirement.

**Format:** `food query d/DATE`

**Example:** `food query d/today`

**Expected output:**

Summary Food Intake for the Day (2021-02-28):\
Tomatoes: 170 kcal\
Onions: 340 kcal\
Total Calories Intake: 510 kcal


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Input weight, height and ideal weight** | `bmi h/height(cm) w/weight(kg) i/ideal_weight(kg)​` <br> e.g., `bmi h/170 w/70 i/80`
**Query weight, height and BMI** | `bmi query`
**Update weight, height and ideal weight** | `bmi update h/height(cm) w/weight(kg) i/ideal_weight(kg)`<br> e.g., `bmi update h/170 w/70 i/80`
**Get diet recommendation based on current BMI** | `plan recommended`
**View active diet plan** | `plan current`
**Select active diet plan** | `start p/plan`<br> e.g., `start p/1`
**List all available diet plans** | `plans`
**View information about a particular diet plan** | `plan \tID​`<br> e.g.,`plan t\1`
**Input carbohydrates, fats and protein intake** | `food d/DATE n/NAME p/PROTEINS c/CARBOHYDRATES f/FATS`<br> e.g., `food d/today n/tomatoes p/10 c/10 f/10`
**List food intake for certain days** | `food query d/DATE`<br> e.g., `food query d/today`
