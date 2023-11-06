package ru.aniglory.data.model.response

import kotlinx.serialization.Serializable
import javax.swing.text.StyledEditorKit.BoldAction

@Serializable
data class BaseResponse(
    val success: Boolean,
    val message: String
)
