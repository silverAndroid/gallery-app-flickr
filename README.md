# Gallery App

Simple gallery app powered by the Flickr API

## Building and running the app

In order to prevent my API key from being committed, I created a secret.properties file which contained it.

Your secret.properties file should look like:

```
API_KEY="..."
```

## Problem

My task was to build a simple gallery application that would show off a series of images which would be fetched from Flickr's REST API.

## Solution

To communicate with the Flickr REST API, I used [**Retrofit**](https://github.com/square/retrofit) with [**Kotlin coroutines**](https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter). I chose coroutines over RxJava because the API was simplistic enough to do API calls but if anything complex like `map` or `filter` was required, I could rely on the builtin functional operators in the Collections APIs provided by Kotlin, and any stream-related operations could be done using channels.

To load images, I used [**Glide**](https://github.com/bumptech/glide) as it was one of the most commonly used libraries in Android development for image loading. I chose it over [**Fresco**](https://github.com/facebook/fresco) because I would be able to manipulate the images as if it was an ImageView while configuring the images to how I need it to be.

For dependency injection, I used [**Dagger**](https://github.com/google/dagger) as it's commonly used in Android development for it and it was the one I'm most familiar with.

## What if I had more time?

If I had more time, I would have:
* written some UI automation tests with Espresso
* added some prefetching optimizations related to Glide so the RecyclerView would scroll smoother
* tested and see if Kotlin Android Extensions works with ViewHolders now
* had 2 model classes for Image (one as a data transfer object, and one to use throughout the app)
    * Since Moshi prefers classes that mirror the JSON, if there was any snake case variables to use, I would've preferred those be switched to camel case variables so I would've created a model class that had the camel case variables.