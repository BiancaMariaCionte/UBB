#include "mainwindow.h"
#include "Tests.h"
#include "Repository.h"
#include "SongController.h"
#include "mediaplayerwidget.h"
#include <QApplication>

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);
    FileRepository fileRepo("songs.txt");
    SongController* ctrl = new SongController(fileRepo);

    MainWindow w(ctrl);
    w.show();
    return a.exec();
    
    //Repository repository; // Create an instance of FileRepository
    //SongController controller(repository); // Pass the repository to the SongController
    
    RepositoryTest repositoryTest;
    repositoryTest.runTests();

    // Test FileRepository class
    FileRepositoryTest fileRepositoryTest;
    fileRepositoryTest.runTests();

    // Test SongController class
    SongControllerTest songControllerTest;
    songControllerTest.runTests();
    
}
