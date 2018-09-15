# PhoneInfo
This is a sample android app written in kotlin and created using the recommendations described in https://developer.android.com/jetpack/docs/guide.

It showcases some of the components of Android Jetpack (ViewModel, LiveData), it uses Dagger for dependency injection and retrofit for networking.

It consists of two activities:

-> MainActivity: displays a SearchView where the user can write the name or the brand of a phone and a RecyclerView that shows the results of the search.

The data is fetched from https://fonoapi.freshpixl.com/ (the github project : https://github.com/shakee93/fonoapi)

-> DetailActivity: displays a list of cards that shows the various specifications of a single phone.

The specifications are organized in the same fashion as https://www.gsmarena.com/ 
