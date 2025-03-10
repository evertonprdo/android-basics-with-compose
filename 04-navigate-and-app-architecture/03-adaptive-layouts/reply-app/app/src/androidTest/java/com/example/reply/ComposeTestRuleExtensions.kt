/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.reply

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * Finds a semantics node from the content description with the given string resource id.
 *
 * The [onNodeWithContentDescription] finder provided by compose ui test API, doesn't support usage
 * of string resource id to find the semantics node from the node's content description.
 * This extension function accesses string resource using underlying activity property
 * and passes it to [onNodeWithContentDescription] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithContentDescription(activity.getString(id))

/**
 * Finds a semantics node from the content description with the given string resource id.
 *
 * The [onNodeWithTag] finder provided by compose ui test API, doesn't support usage of
 * string resource id to find the semantics node from the node's test tag.
 * This extension function accesses string resource using underlying activity property
 * and passes it to [onNodeWithTag] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithTag(activity.getString(id))

// Helper for onNodeWithText(activity.getString(R.string.*))
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTextForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))