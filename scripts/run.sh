javac -d "out" -cp lib/json-simple-1.1.1.jar src/*.java
build_status="$?"
echo Build status: $build_status
[ $build_status -eq "0" ] && java -cp "out:lib/*" Menu
