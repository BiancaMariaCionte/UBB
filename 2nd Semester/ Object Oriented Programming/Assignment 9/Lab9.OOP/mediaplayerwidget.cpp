//#include "mediaplayerwidget.h"
//
//
//MediaPlayerWidget::MediaPlayerWidget(QWidget* parent)
//    : QWidget(parent), m_player(nullptr), m_videoWidget(nullptr),
//    m_openButton(nullptr), m_playButton(nullptr), m_stopButton(nullptr)
//{
//    QVBoxLayout* layout = new QVBoxLayout(this);
//
//    m_player = new QMediaPlayer(this);
//    m_videoWidget = new QVideoWidget(this);
//    m_player->setVideoOutput(m_videoWidget);
//
//    m_openButton = new QPushButton("Open Video", this);
//    connect(m_openButton, &QPushButton::clicked, this, &MediaPlayerWidget::openVideo);
//
//    m_playButton = new QPushButton("Play", this);
//    connect(m_playButton, &QPushButton::clicked, m_player, &QMediaPlayer::play);
//
//    m_stopButton = new QPushButton("Stop", this);
//    connect(m_stopButton, &QPushButton::clicked, m_player, &QMediaPlayer::stop);
//
//    layout->addWidget(m_openButton);
//    layout->addWidget(m_videoWidget);
//    layout->addWidget(m_playButton);
//    layout->addWidget(m_stopButton);
//}
//
//void MediaPlayerWidget::openVideo()
//{
//    QString videoPath = QFileDialog::getOpenFileName(this, "Open Video", QString(), "Video Files (*.mp4 *.avi)");
//    if (!videoPath.isEmpty()) {
//        m_player->setMedia(QUrl::fromLocalFile(videoPath));
//    }
//}