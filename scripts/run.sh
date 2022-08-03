javac -d "out" -cp "lib/*" "src/*.java"
build_status="$?"
echo Build status: $build_status
[ $build_status -eq "0" ] && java -cp "out:lib/*" Game
