/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.nbt.filetype

import com.demonwav.mcdev.nbt.MalformedNbtFileException
import com.demonwav.mcdev.nbt.Nbt
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.util.io.ByteSequence
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

class NbtFileTypeDetector : FileTypeRegistry.FileTypeDetector {
    override fun getVersion() = 1

    override fun detect(file: VirtualFile, firstBytes: ByteSequence, firstCharsIfText: CharSequence?): FileType? {
        return try {
            Nbt.buildTagTree(file.inputStream, 100)
            NbtFileType
        } catch (e: MalformedNbtFileException) {
            null
        } catch (e: IOException) {
            null
        }
    }
}
