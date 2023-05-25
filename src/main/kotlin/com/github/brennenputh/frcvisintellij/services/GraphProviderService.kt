package com.github.brennenputh.frcvisintellij.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.github.brennenputh.frcvisintellij.MyBundle

@Service(Service.Level.PROJECT)
class GraphProviderService(project: Project) {
    val ready = false

    init {
        thisLogger().info(MyBundle.message("projectService", project.name))
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    fun getRandomNumber() = (1..100).random()

    fun getPSIFile() {
    }
}
