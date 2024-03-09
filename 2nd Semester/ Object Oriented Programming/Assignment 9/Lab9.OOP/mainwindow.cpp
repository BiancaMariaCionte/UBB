#include "mainwindow.h"
#include "SongException.h"
#include "Repository.h"


MainWindow::MainWindow(SongController* ctrl, QWidget* parent)
    : QMainWindow(parent)
{
    m_ctrl = ctrl;
    this->setupUI();

    QShortcut* shortcutUndo = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Z), this);
    QShortcut* shortcutRedo = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Y), this);

    // Connect shortcuts to slots
    connect(shortcutUndo, &QShortcut::activated, this, &MainWindow::handleUndo);
    connect(shortcutRedo, &QShortcut::activated, this, &MainWindow::handleRedo);
}

MainWindow::~MainWindow()
{
    delete m_leftVLayout;
    delete m_centralWidget;
}

void MainWindow::setupUI()
{
    m_centralWidget = new QWidget();
    m_leftVLayout = new QVBoxLayout();
    m_leftButtons = new QGridLayout();
    m_mainLayout = new QHBoxLayout();
    m_rightVLayout = new QVBoxLayout();
    m_rightButton = new QHBoxLayout();

    m_lineEdit = new QLineEdit(this);
    m_numbersList = new QListWidget(this);
    m_addNumberBtn = new QPushButton("Add", this);
    QPushButton* clearListBtn = new QPushButton("Clear", this);

    QGridLayout* layout = new QGridLayout();
    layout->addWidget(m_lineEdit, 0, 1);
    layout->addWidget(m_addNumberBtn, 1, 1);
    layout->addWidget(clearListBtn, 2, 1);
    layout->addWidget(m_numbersList, 0, 0, 3, 1);

    QWidget* w = new QWidget(this);
    w->setLayout(layout);
    setCentralWidget(w);

    connect(m_addNumberBtn, &QPushButton::clicked, this, &MainWindow::onButtonPressed);

    connect(clearListBtn, &QPushButton::clicked, this, [this]() {
        m_numbersList->clear();
        emit lambdaOver();
        });

    connect(this, &MainWindow::lambdaOver, this, []() {
        qDebug() << "After lambda is over";
        });
    
        
    //"Playlist" box
    m_listPlaylistLabel = new QLabel("Playlist:");
    m_listPlaylist = new QListWidget();

    
    //"All songs" box
    m_listSongsLabel = new QLabel("All songs:");
    m_listSongs = new QListWidget();
    m_leftFormLayout = new QFormLayout();

    m_titleLable = new QLabel("Title:");
    m_titleText = new QLineEdit();

    m_artistLable = new QLabel("Artist:");
    m_artistText = new QLineEdit();

    m_yearLable = new QLabel("Year:");
    m_yearText = new QLineEdit();

    m_lyricsLable = new QLabel("Lyrics:");
    m_lyricsText = new QLineEdit();

    m_linkLable = new QLabel("Link:");
    m_linkText = new QLineEdit();


    // RIGHT Buttons creation
    m_playButton = new QPushButton("Play");
    m_nextButton = new QPushButton("Next");

    //RIGHT Buttons position 
    m_rightButton->addWidget(m_playButton);
    m_rightButton->addWidget(m_nextButton);

    //LEFT Buttons creation
    m_addButton = new QPushButton("Add");
    m_deleteButton = new QPushButton("Delete");
    m_filterButton = new QPushButton("Filter");
    m_updateButton = new QPushButton("Update");
    m_arrowButton = new QPushButton(">");

    m_sortTitleButton = new QPushButton("Sort by Title");
    m_sortArtistButton = new QPushButton("Sort by Artist");
    m_lyricsButton = new QPushButton("View Lyrics");
    m_randomPlaylistButton = new QPushButton("Create Random Playlist");

    //LEFT Buttons position 
    m_leftButtons->addWidget(m_addButton, 0, 0);
    m_leftButtons->addWidget(m_deleteButton, 0, 1);
    m_leftButtons->addWidget(m_updateButton, 0, 2);
    m_leftButtons->addWidget(m_filterButton, 1, 0);
    m_leftButtons->addWidget(m_sortTitleButton, 1,1);
    m_leftButtons->addWidget(m_sortArtistButton, 1,2 );
    m_leftButtons->addWidget(m_lyricsButton, 2,0);
    m_leftButtons->addWidget(m_randomPlaylistButton, 2,1);

    //LEFT LAYOUTS
    m_leftFormLayout->addRow(m_titleLable, m_titleText);
    m_leftFormLayout->addRow(m_artistLable, m_artistText);
    m_leftFormLayout->addRow(m_yearLable, m_yearText);
    m_leftFormLayout->addRow(m_lyricsLable, m_lyricsText);
    m_leftFormLayout->addRow(m_linkLable, m_linkText);

    
    m_leftVLayout->addWidget(m_listSongsLabel);
    m_leftVLayout->addWidget(m_listSongs);
    m_leftVLayout->addLayout(m_leftFormLayout);
    m_leftVLayout->addLayout(m_leftButtons);

    //RIGHT LAYOUT
    m_rightVLayout->addWidget(m_listPlaylistLabel);
    m_rightVLayout->addWidget(m_listPlaylist);
    m_rightVLayout->addLayout(m_rightButton);


    //main layout
    m_mainLayout->addLayout(m_leftVLayout);
    m_mainLayout->addWidget(m_arrowButton);
    m_mainLayout->addLayout(m_rightVLayout);
    


    m_centralWidget->setLayout(m_mainLayout);
    this->setCentralWidget(m_centralWidget);

    connect(m_addButton, &QPushButton::clicked, this, &MainWindow::onAddButton);
    connect(m_deleteButton, &QPushButton::clicked, this, &MainWindow::onDeleteButton);
    connect(m_filterButton, &QPushButton::clicked, this, &MainWindow::onFilterButton);
    connect(m_arrowButton, &QPushButton::clicked, this, &MainWindow::onArrowButton);
    connect(m_updateButton, &QPushButton::clicked, this, &MainWindow::onUpdateButton);
    connect(m_sortTitleButton, &QPushButton::clicked, this, &MainWindow::onSortTitleButton);
    connect(m_sortArtistButton, &QPushButton::clicked, this, &MainWindow::onSortArtistButton);
    connect(m_lyricsButton, &QPushButton::clicked, this, &MainWindow::onLyricsButton);
    connect(m_randomPlaylistButton, &QPushButton::clicked, this, &MainWindow::onRandomPlaylistButton);
    
    std::vector<Song> allSongs = m_ctrl->getAll();
    for (auto s : allSongs) {
        QListWidgetItem* itm = new QListWidgetItem(QString::fromStdString(s.toString() ));
        m_listSongs->addItem(itm);

    }
}
void MainWindow::onButtonPressed()
{
    m_numbersList->addItem(m_lineEdit->text());
}

void MainWindow::onLambdaOver()
{
    qDebug() << "After lambda is over";
}

void MainWindow::handleUndo() {
    m_ctrl->undo();
}

void MainWindow::handleRedo() {
    m_ctrl->redo();
}
void MainWindow::onAddButton()
{
    QString title = m_titleText->text(); //QLineEdit - let's you insert text
    QString artist = m_artistText->text();
    QString yearStr = m_yearText->text();
    QString lyrics = m_lyricsText->text();
    QString link = m_linkText->text();

    // Validate input
    if (title.isEmpty() || artist.isEmpty() || yearStr.isEmpty() || lyrics.isEmpty() || link.isEmpty()) {
        QMessageBox::warning(this, "Error", "Please fill in all fields.");
        return;
    }

    bool ok;
    int year = yearStr.toInt(&ok);
    if (!ok) {
        QMessageBox::warning(this, "Error", "Invalid year. Please enter a valid number.");
        return;
    }


    // Create a new Song object with the input data
    /* {
        Song song{ title.toStdString(), artist.toStdString(), year, lyrics.toStdString(), link.toStdString() };
    }
    */
    //Song song; // Declare the object here
    //song.title = title.toStdString();
    //song.artist = artist.toStdString();
    //song.year = year;
    //song.lyrics = lyrics.toStdString();
    //song.link = link.toStdString();

    // Add the song to the list of songs
    //m_songs.push_back(song);
    m_ctrl->addSong(title.toStdString(), artist.toStdString(), year, lyrics.toStdString(), link.toStdString());

    m_listSongs->addItem(title+" "+ artist + " "+ QString::number(year) +" " +lyrics+" "+ link);

    // Clear the input fields
    m_titleText->clear(); //QLineEdit
    m_artistText->clear();
    m_yearText->clear();
    m_lyricsText->clear();
    m_linkText->clear();
}


void MainWindow::onUpdateButton()
{
    QMessageBox::information(this, "Update", "Not implemented yet");
}

void MainWindow::onFilterButton()
{
    QMessageBox::information(this, "Filter", "Not implemented yet");
}
void MainWindow::onDeleteButton()
{
    // Get the selected item from the songs list
    QListWidgetItem* selectedItem = m_listSongs->currentItem();
    if (selectedItem) //if an item is selected
    {

        // Get the text of the selected item
        QString selectedText = selectedItem->text();

        // Extract the title from the selected text
        QString title = selectedText.section(' ', 0, 0);

        // Remove the song from the controller
        m_ctrl->removeSong(title.toStdString());

        // Remove the selected item from the songs list
        m_listSongs->takeItem(m_listSongs->row(selectedItem));

        // Delete the selected item
        delete selectedItem;

        // Get the text of the selected item
        //QString songText = selectedItem->text();

        /*
        // Extract the title from the selected item text
        QStringList parts = songText.split(' '); //split the songText into a list of substrings
        if (!parts.isEmpty())
        {
            QString title = parts[0]; //get the first element from "parts" and assign it to title

            // Find the song with the matching title and remove it from the songs list
            for (auto it = m_songs.begin(); it != m_songs.end(); ++it) {
                if (QString::fromStdString(it->getTitle()) == title)
                {
                    m_songs.erase(it);
                    break;
                }
            }

            // Remove the selected item from the list widget
            m_listSongs->takeItem(m_listSongs->row(selectedItem)); //QListWidget
            //remove the item at the specified row index from the list
            //get the row number of the selectedItem within the list widget
        }*/
    }
}
void MainWindow::onArrowButton()
{
    // Get the selected item from the songs list
    QListWidgetItem* selectedItem = m_listSongs->currentItem();
    if (!selectedItem) {
        QMessageBox::warning(this, "Error", "Please select a song from the playlist.");
        return;
    }

    // Get the text of the selected item
    QString selectedText = selectedItem->text();

    // Split the text into parts
    QStringList parts = selectedText.split(" ");

    if (parts.size() != 5) {
        QMessageBox::warning(this, "Error", "Invalid song format.");
        return;
    }

    // Extract the title, artist, and year from the parts
    QString title = parts[0];
    QString artist = parts[1];
    QString yearStr = parts[2];
    QString lyrics = parts[3];
    QString link = parts[4];

    // Create a new song object with the extracted information
    Song selectedSong(title.toStdString(), artist.toStdString(), yearStr.toInt(), lyrics.toStdString(), link.toStdString());

    // Remove the selected item from the songs list
    //m_listSongs->takeItem(m_listSongs->row(selectedItem));

    // Add the selected song to the playlist
    m_ctrl->addSongToPlaylist(selectedSong);

    // Add the selected item to the playlist
    QListWidgetItem* newItem = new QListWidgetItem(selectedText);
    m_listPlaylist->addItem(newItem);
}

void MainWindow::onSortTitleButton() {
    // Sort the songs by title
    m_ctrl->getSortedByTitle(true);

}

void MainWindow::onSortArtistButton() {
    // Sort the songs by artist
    m_ctrl->getSortedByArtist(true);

}

void MainWindow::onLyricsButton() {
    // Get the selected item from the songs list
    QListWidgetItem* selectedItem = m_listSongs->currentItem();
    if (!selectedItem) {
        QMessageBox::warning(this, "Error", "Please select a song.");
        return;
    }

    // Get the text of the selected item
    QString selectedText = selectedItem->text();

    // Extract the title from the selected text
    QString title = selectedText.section(' ', 0, 0);

    // Get the lyrics of the selected song
    std::string lyrics = m_ctrl->getLyrics(title.toStdString());

    // Display the lyrics in a message box
    QMessageBox::information(this, "Lyrics", QString::fromStdString(lyrics));
}

void MainWindow::onRandomPlaylistButton() {
    // Get the number of songs for the random playlist
    bool ok;
    int n = QInputDialog::getInt(this, "Random Playlist", "Enter the number of songs:", 1, 1, INT_MAX, 1, &ok);
    if (!ok) {
        return;
    }

    // Generate a random playlist
    m_ctrl->generateRandomPlaylist(n);

}


    /*
    // Move the selected song from the songs list to the playlist
    // Get the selected item from the songs list
    QListWidgetItem* selectedItem = m_listSongs->currentItem();
    if (selectedItem) 
    {
        // Get the text of the selected item
        QString songText = selectedItem->text();
        // Extract the title from the selected item text
        QStringList parts = songText.split(' ');
        if (!parts.isEmpty()) 
        {
            QString title = parts[0]; // //get the first element from "parts" and assign it to title

            // Find the song with the matching title and add it to the playlist
            for (auto it = m_songs.begin(); it != m_songs.end(); ++it) 
            {
                if (QString::fromStdString(it->getTitle()) == title)
                {
                    // Add the song to the playlist
                    m_playlist.push_back(*it);

                    // Remove the song from the songs list
                    m_songs.erase(it);

                    // Remove the selected item from the list widget
                    m_listSongs->takeItem(m_listSongs->row(selectedItem));

                    // Add the selected song to the playlist list widget
                    m_listPlaylist->addItem(songText);
                    break;
                }
            }
        }
    }
    */
