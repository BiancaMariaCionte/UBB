/********************************************************************************
** Form generated from reading UI file 'Lab9OOP.ui'
**
** Created by: Qt User Interface Compiler version 6.2.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAB9OOP_H
#define UI_LAB9OOP_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Lab9OOPClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Lab9OOPClass)
    {
        if (Lab9OOPClass->objectName().isEmpty())
            Lab9OOPClass->setObjectName(QString::fromUtf8("Lab9OOPClass"));
        Lab9OOPClass->resize(600, 400);
        menuBar = new QMenuBar(Lab9OOPClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        Lab9OOPClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Lab9OOPClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        Lab9OOPClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(Lab9OOPClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        Lab9OOPClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(Lab9OOPClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        Lab9OOPClass->setStatusBar(statusBar);

        retranslateUi(Lab9OOPClass);

        QMetaObject::connectSlotsByName(Lab9OOPClass);
    } // setupUi

    void retranslateUi(QMainWindow *Lab9OOPClass)
    {
        Lab9OOPClass->setWindowTitle(QCoreApplication::translate("Lab9OOPClass", "Lab9OOP", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Lab9OOPClass: public Ui_Lab9OOPClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB9OOP_H
