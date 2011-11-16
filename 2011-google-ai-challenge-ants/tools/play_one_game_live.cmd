@echo off

python "%~dp0playgame.py" -So --engine_seed 42 --player_seed 42 --end_wait=0.25 --verbose --log_dir game_logs --turns 1000 --map_file "%~dp0maps\maze\maze_04p_01.map" %* "python ""%~dp0sample_bots\python\HunterBot.py""" "python ""%~dp0sample_bots\python\LeftyBot.py""" "python ""%~dp0MyBots\TutorialBot.py3""" "python ""%~dp0MyBots\MyBot.py3""" | java -jar visualizer.jar

::python playgame.py "python MyBot.py" "python tools/sample_bots/python/HunterBot.py" --map_file tools/maps/example/tutorial1.map --log_dir game_logs --turns 60 --scenario --food none --player_seed 7 --verbose -e
