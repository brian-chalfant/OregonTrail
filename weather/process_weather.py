import pandas as pd
import os

def get_csv(csvfile):
    spamreader = csv.reader(csvfile, delimiter=',')
    for row in spamreader:
        print(', '.join(row))

def main():
    """_summary_
    """
    csv_path = "weather/weather.csv"
    output_path = "resources/generated/trail_weather.csv"
    with open(csv_path, "r") as csvfile:
        df = pd.read_csv(csvfile)
    
    state_path = "resources/objects/state.txt"
    with open(state_path, "r") as statefile:
        state_list = statefile.read().splitlines()

    df = df[df['Station.State'].isin(state_list)]

    conds = ['min', 'max', 'mean']
    x = df.groupby(["Station.State", "Date.Month"], as_index=False).agg({'Data.Precipitation':conds, 
        'Data.Temperature.Avg Temp': 'mean', 
        'Data.Temperature.Max Temp': 'max', 
        'Data.Temperature.Min Temp': 'min', 
        "Data.Wind.Speed": conds
    })

    x.columns = [
        "state",
        "month",
        "rain_min",
        "rain_max",
        "rain_mean",
        "temp_mean",
        "temp_max",
        "temp_min",
        "wind_speed_min",
        "wind_speed_max",
        "wind_speed_mean"
    ]

    # print(x)
    parent_dir = os.path.dirname(output_path)
    if not os.path.isdir(parent_dir): os.mkdir(parent_dir)

    with open(output_path, "w") as out_file:
        x.to_csv(out_file)
    print("Successfully wrote weather data to:", output_path)

if __name__ == "__main__":
    main()