# Android Clean - Arch components


Using `Dagger2` to manager class dependencies, using `@BindsInstance` to make the Context available in the graph

> Database Module needs a context to initialize `Room`

```
@Module
class DatabaseModule{

    @Provides
    @Singleton
    fun providesRoom(context: Context) : AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database-name")
            .build()
    }
}
```

Also using many `RxJava2` approaches to transform complex objects into simpler objects.

````
apiService.get()
  .flatMapIterable { moviesMap ->
       moviesMap.entries.map { mapMovie ->
             Movie(
                  mapMovie.key,
                  mapMovie.value.rating,
                  mapMovie.value.synopsis,
                  mapMovie.value.title,
                  mapMovie.value.year
              )
         }
       }.toList()
````

### Offline-first


#### Getting data from API

````
 private fun getMoviesFromAPI(): Observable<MutableList<Movie>> {
        return apiService.get()
            .flatMapIterable { moviesMap ->
                moviesMap.entries.map { mapMovie ->
                    Movie(
                        mapMovie.key,
                        mapMovie.value.rating,
                        mapMovie.value.synopsis,
                        mapMovie.value.title,
                        mapMovie.value.year
                    )
                }
            }
            .toList()
            .doAfterSuccess {
                appDatabase.movieDAO().insertAllMovies(it)
            }
            .toObservable()

 }
````

#### Getting data from local database

````
private fun getMoviesFromDB(): Observable<MutableList<Movie>> {
    return Observable.fromCallable {
        appDatabase.movieDAO().getAllMovie()
    }
}
````

#### Combining remote and local 

````
fun getMovies(): Observable<MutableList<Movie>> {     
    return Observable.concatArray(getMoviesFromDB(), getMoviesFromAPI())
}
````

# Libraries used
* [AndroidX](https://developer.android.com/jetpack/androidx/)
* [Dagger 2](https://google.github.io/dagger/)
* [Retrofit 2](http://square.github.io/retrofit/)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
* [RxJava 2](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Jetpack Navigation](https://developer.android.com/guide/navigation/)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/)

# License

    Copyright 2018 Anderson Caxias

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

