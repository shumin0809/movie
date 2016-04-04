# movie

Description:

This is a Android native application that allows user to search for movies 
using the open movie database search API.

There are three main views of the app.

The first one is the search view. User could type any data he/she wants 
to perform the search. Of course, some searches are not allows such as
each keyword should at least two charactors. 
After searched, the app will fetch data (Movie only) from the server.
The data will be presented as a list of card views. Each card view represents
a movie.

If users likes a movie, they could save it to their favorite list by clicking
the "+" icon onto the top right corner of the card view. Then the movie will 
be save to the second view (favorite view). Users could delete the movie from
the favorite list as well.

If users click onto the card view in the search or item view in the favorite,
a detail view will be poped up so that user could read more detail about the 
movie.


Technology:

1. Support design libraries
  - Lastest android ui libraries.
  - Simple to use and very beautiful!
2. Retrofit
  - A type-safe HTTP client for Android and Java
  - It's very easy to use
  - It's much faster than the other similar libraries.
3. Picasso
  - It's a very powerful image downloading library for Android.
4. event bus
  - An helpful library to simplifies communicate between activities, fragments, view.

Note:

1. For the search, I made API calls like "http://www.omdbapi.com/?s=Batman&page=1".
   These calls will return a list of movie, each one only has 5 field 
   (poster, title, type, year, imdbID). Therefore, I only displayed poster, title,
   and year in the search. Then I show director and summary in the detail view.

2. Favorite movies will be save into local database, could perform save/delete.
   Duplicate objects couldn't be saved. The notification will show in search result
   when user try to save a duplicate movie.

3. Pagination is support in search view.

4. To run the apk, adb -d install path/to/your/app-debug.apk

