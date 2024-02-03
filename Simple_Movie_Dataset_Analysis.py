# Civan Rıdar Öztekin
# Sample Dataset
movie_data = [
    {"title": "Inception", "year": 2010, "genre": "Sci-Fi", "rating": 8.7},
    {"title": "Titanic", "year": 1997, "genre": "Romance", "rating": 7.8},
]

# Function to load data
def load_data():
    movieList = movie_data
    movieList.append({"title":"Back to the Future","year":1985,"genre":"Sci-Fi","rating":8.5})
    movieList.append({"title":"The Matrix","year":1999,"genre":"Sci-Fi","rating":8.7})
    movieList.append({"title":"The Fast and the Furious","year":2001,"genre":"Action","rating":6.8})
    movieList.append({"title":"Catch Me If You Can","year":2002,"genre":"Drama","rating":8.1})
    movieList.append({"title":"WALL-E","year":2008,"genre":"Animation","rating":8.4})
    movieList.append({"title":"3 Idiots","year":2009,"genre":"Comedy","rating":8.3})
    movieList.append({"title":"Rush","year":2013,"genre":"Action","rating":8.0})
    movieList.append({"title":"Inside Out","year":2015,"genre":"Animation","rating":8.1})
    movieList.append({"title":"Mad Max: Fury Road","year":2015,"genre":"Action","rating":8.1})
    movieList.append({"title":"Baby Driver","year":2017,"genre":"Action","rating":7.5})
    movieList.append({"title":"Avengers: Infinity War","year":2018,"genre":"Action","rating":8.4})
    movieList.append({"title":"Joker","year":2019,"genre":"Drama","rating":8.3})
    movieList.append({"title":"Ford v Ferrari","year":2019,"genre":"Action","rating":8.1})
    movieList.append({"title":"El Camino: A Breaking Bad Movie","year":2019,"genre":"Action","rating":7.3})
    movieList.append({"title":"Spider-Man: Across the Spider-Verse","year":2023,"genre":"Animation","rating":8.6})
    return movieList
    

# Function to calculate basic statistics
def basic_statistics(movies):
    numberOfMovies = len(movies)
    totalRating = 0.0
    # Calculate sum of all ratings
    for movie in movies:
        totalRating+=movie["rating"]
    print("Basit Statistics:")
    print("Total number of movies:",numberOfMovies)
    print("Average user rating for all movies:",round(totalRating/numberOfMovies,2))
    print()

# Function for genre analysis
def genre_analysis(movies):
    print("Genre Analysis:")
    # Dictionary for storing count of movies for each genre
    genreCountDict = {}
    # For loop to fill dictionary
    for movie in movies:
        if movie["genre"] in genreCountDict:
            genreCountDict[movie["genre"]]+=1
        else:
            genreCountDict[movie["genre"]]=1
    # A starting value for possible most common genre
    mostCommonGenre = movies[0]["genre"]
    # For loop for both determine most common genre and show the genres with their counters
    for genre in genreCountDict:
        if genreCountDict[mostCommonGenre]<genreCountDict[genre]:
            mostCommonGenre = genre
        print(genre+":",genreCountDict[genre],"movie")
    print("Most common genre:",mostCommonGenre)
    print()

# Function for yearly analysis
def yearly_analysis(movies):
    print("Yearly Analysis:")
    # Dictionary for storing count of movies released each year
    yearCountDict = {}
    # For loop to fill dictionary
    for movie in movies:
        if movie["year"] in yearCountDict:
            yearCountDict[movie["year"]]+=1
        else:
            yearCountDict[movie["year"]]=1
    # A starting value for possible most common year
    mostCommonYear = movies[0]["year"]
    # For loop for both determine most common year and show the releasing years with their conters
    for year in yearCountDict:
        if yearCountDict[mostCommonYear]<yearCountDict[year]:
            mostCommonYear = year
        print(str(year)+":",yearCountDict[year],"movie")
    print("Most common year:",mostCommonYear)
    print()

# Function to find top-rated movies
def top_rated_movies(movies):
    # A list to store top 5 movies' datas. Default value of ratings setted as -1 to be able to compare
    topFiveMovies=[{"rating":-1.0},{"rating":-1.0},{"rating":-1.0},{"rating":-1.0},{"rating":-1.0}]
    for movie in movies:
        # Check if movie's rating is should be in current top 5 list
        for i in range(len(topFiveMovies)):
            #Determine if movie should be in top 5, where it should be
            if topFiveMovies[i]["rating"] < movie["rating"]:
                # Shift top 5 list to the right starting from nth index which is the index the movie should be stored
                # Thanks to shifting starting from nth index, the list will be always sorted 
                shiftListToRightAfterN(topFiveMovies,i)
                # Store the movie where it should be stored
                topFiveMovies[i] = movie
                break
    print("Top 5 Movies:")
    for i in range(len(topFiveMovies)):
        # If there is less than 5 movies in the dataset, don't allow print default setted datas
        if topFiveMovies[i]["rating"] >= 0.0:
            print(str(i+1)+".",topFiveMovies[i]["title"],end=" ")
            print("("+str(topFiveMovies[i]["year"])+")",end=" ")
            print("-",topFiveMovies[i]["genre"],"-",end=" ")
            print("Rating:",topFiveMovies[i]["rating"])
    print()
        

# Function for user interaction
def user_interaction(movies):
    print("User Interaction:")
    nameToSearch = input("Enter a movie title: ")
    isMovieFound = False
    # Search given title in the dataset
    for movie in movies:
        if movie["title"] == nameToSearch:
            isMovieFound = True
            print("Movie found!")
            print("Title:",movie["title"])
            print("Year:",movie["year"])
            print("Genre:",movie["genre"])
            print("Rating:",movie["rating"])
            break
    if not isMovieFound:
        print("Movie couldn't found!")

# Function shifts given list to the right, starts from nth index and last item will be fall
def shiftListToRightAfterN(listToBeShifted: list,n:int):
    rightIndex = len(listToBeShifted) - 1 
    while n < rightIndex:
        listToBeShifted[rightIndex]=listToBeShifted[rightIndex-1]
        rightIndex-=1

# Main Program
if __name__ == "__main__":
    # Load Data
    movie_data = load_data()

    # Basic Statistics
    basic_statistics(movie_data)

    # Genre Analysis
    genre_analysis(movie_data)

    # Yearly Analysis
    yearly_analysis(movie_data)

    # Top-Rated Movies
    top_rated_movies(movie_data)

    # User Interaction
    user_interaction(movie_data)
