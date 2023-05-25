package com.github.brennenputh.frcvisintellij.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil


class OpenGraphAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile: PsiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return
        val offset: Int = editor.caretModel.offset

        val infoBuilder = StringBuilder()
        val element = psiFile.findElementAt(offset)
        infoBuilder.append("Element at caret: ").append(element).append("\n")
        if (element != null) {
            val containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
            infoBuilder
                .append("Containing method: ")
                .append(containingMethod?.name ?: "none")
                .append("\n")
            if (containingMethod != null /*&& containingMethod.returnType == PsiType.getTypeByName("CommandBase", event.project!!, GlobalSearchScope.allScope(event.project!!)) */) {
                val containingClass: PsiClass? = containingMethod.containingClass
                infoBuilder
                    .append("Containing class: ")
                    .append(containingClass?.name ?: "none")
                    .append("\n")
                infoBuilder.append("Local variables:\n")
                containingMethod.accept(object : JavaRecursiveElementVisitor() {
                    override fun visitMethodCallExpression(expression: PsiMethodCallExpression) {
                        super.visitMethodCallExpression(expression)
                        println("Found " + expression.resolveMethod()!!.containingClass)
                    }

                    override fun visitNewExpression(expression: PsiNewExpression) {
                        super.visitNewExpression(expression)
                        println("Found " + expression.resolveMethod()!!.containingClass)
                    }
                })
            }
        }
        Messages.showMessageDialog(event.project, infoBuilder.toString(), "PSI Info", null)
    }

    fun searchCommandTree(topLevelMethod: PsiMethod) {

    }
}
