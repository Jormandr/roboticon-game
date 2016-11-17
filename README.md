# Roboticon Colony of York

This is team [Jormandr](https://jormandr.co.uk)'s implementation of the 'Roboticon Colony of York' project for 2016/17 SEPR of the [University of York](http://cs.york.ac.uk)'s Computer Science course. The game is a turn-based strategy game which might be considered either management or territorial control (think Civ) and is written in Java with LibGDX

This repository is mainly for code, so most documentation can be found at [our website](https://jormandr.co.uk)

We currently have no official license, so the code, while available, is 'all rights reserved'

## Contributing

To set up this project:

* Install Eclipse - the recommended version is 'Neon'
* Clone the repository
* Choose 'File -> Import -> General -> Existing Projects into Workspace'
    * Select the root folder of the whole repository
    * Click 'Finish'
* Right click on 'roboticongame-desktop' in the package explorer (close the welcome screen) and select 'Run As -> Java Application -> DesktopLauncher'
    * This will fail (except maybe if you put it in '/home/mark/src/roboticon-game'). Now open 'Run As -> Run Configurations'
    * On the 'Arguments' tab, set the working directory to '${project-path}/../roboticongame-core/assets/'
* The game should now run correctly (displaying a mostly red screen with 'Bad Logic Games' in the bottom left)

To commit to the repository, it is recommended to use Git by hand, not by IDE integration. The current style guide is "Press CTRL-Shift-F and let Eclipse handle it"
