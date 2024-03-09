#ifndef MAINWINDOW_H
#define MAINWINDOW_H // preprocessor directives that prevent the file from being included more than once

#include <QMainWindow> //provides the main application window
#include <QVBoxLayout> //arranges widgets vertically
#include <QLabel> //displays text
#include <QTextEdit> //allows the user to input or edit multi-line text
#include <QLineEdit> //allows the user to input or edit a single line of text
#include <QFormLayout> //arranges widgets in a form layout
#include<QPushButton>
#include<QGridLayout>
#include <QHBoxLayout> 
#include <QTableWidget> 
#include<QMessageBox>
#include <QListWidget>
#include <QLineEdit>
#include <vector>
#include <QShortcut>
#include <QListWidgetItem>
#include <QInputDialog>
#include "SongController.h"

#include "Song2.h"
#include "SongException.h"

class MainWindow : public QMainWindow

{
    Q_OBJECT; //used to enable some of Qt's features such as signals and slots
//public slots:
 //  void onAddReleased();

public:
    //The constructor takes a `QWidget*` as an optional argument, which defaults to `nullptr`
    //This allows the class to be instantiated with a parent widget, but it is not required
    MainWindow(SongController* ctrl, QWidget* parent = nullptr);
    ~MainWindow();
public slots:
    void onButtonPressed();
    void onLambdaOver();
    void onAddButton();
    void onDeleteButton();
    void onUpdateButton();
    void onFilterButton();
    void onArrowButton();
    void onSortTitleButton();
    void onSortArtistButton();
    void onLyricsButton();
    void onRandomPlaylistButton();
    void handleUndo();
    void handleRedo();


signals:
    void lambdaOver();

private:
    //This function will be used to set up the user interface of the `MainWindow` class
    void setupUI();
    //void addSongToPlaylist(const Song& song);

    QLineEdit* m_lineEdit;
    QListWidget* m_numbersList;
    QPushButton* m_addNumberBtn;

    //void displayErrorMessage(const QString& message);

    QWidget* m_centralWidget;// `QWidget` pointer which will be used as the central widget of the `QMainWindow`
    QVBoxLayout* m_leftVLayout; // `QVBoxLayout` pointer will be used to arrange widgets vertically on the left side of the main window
    QFormLayout* m_leftFormLayout; // `QFormLayout` pointer, which will be used to arrange widgets in a form layout
    QGridLayout* m_leftButtons;
    QHBoxLayout* m_mainLayout;
    QVBoxLayout* m_rightVLayout;
    QHBoxLayout* m_rightButton;
    // widgets that will be used in the form layout
    QLabel* m_titleLable;
    QLineEdit* m_titleText;

    QLabel* m_artistLable; //`QLabel` pointers for the labels of the form fields
    QLineEdit* m_artistText;

    QLabel* m_yearLable;
    QLineEdit* m_yearText;

    QLabel* m_lyricsLable;
    QLineEdit* m_lyricsText;

    QLabel* m_linkLable;
    QLineEdit* m_linkText; // 'QLineEdit` and `QTextEdit` pointers for the form fields themselves 

    QLabel* m_listSongsLabel;
    QListWidget* m_listSongs;

    QLabel* m_listPlaylistLabel;
    QListWidget* m_listPlaylist;

    //left Buttons
    QPushButton* m_addButton;
    QPushButton* m_deleteButton;
    QPushButton* m_updateButton;
    QPushButton* m_filterButton;

    QPushButton* m_sortTitleButton;
    QPushButton* m_sortArtistButton;
    QPushButton* m_lyricsButton;
    QPushButton* m_randomPlaylistButton;

    //main button

    QPushButton* m_arrowButton;

    //right Buttons
    QPushButton* m_playButton;
    QPushButton* m_nextButton;

    //lists
    //QList<Song> m_songs; // Store all songs
    SongController* m_ctrl;

    QList<Song> m_playlist; // Store playlist songs

    QShortcut* shortcutUndo;


};

#endif // MAINWINDOW_H

//This line marks the end of the header file. 
//The `#endif` directive ends the conditional
//compilation started with the `#ifndef` directive at the beginning of the file
// It indicates that the #endif statement is related to the #ifndef statement with 
//the symbol MAINWINDOW_H, which is a common convention for avoiding multiple includes
//of the same header file in C++ programs.
