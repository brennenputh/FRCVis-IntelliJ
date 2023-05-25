package com.github.brennenputh.frcvisintellij

import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType
import com.intellij.psi.PsiTypes

class CommandEntry(val parent: PsiMethod) {
    private val children = mutableMapOf<String, PsiType>()

    fun addChild(method: PsiMethod) {
        if (method.isConstructor) {
            method.containingClass
            return
        }
        children.put(method.name, method.returnType ?: PsiTypes.nullType())
    }
}