# Stock'n'Roll
Stock'n'Roll is an Android application designed to help users manage their pantry and discover recipes based on available ingredients. With a natural and colourful interface, the app is personalised for home cooks, busy parents, young adults, and food enthusiasts, enabling them to keep track of ingredients, find recipes, and make meal planning easier.

## Features
    *Pantry Management:
    
        - Add ingredients to the pantry via a built-in ingredient search powered by the Spoonacular API.
        
        - View a list of pantry ingredients with details such as expiry date, stock levels, and names.
        
        - Filter ingredients by category (e.g., produce, dairy & eggs, meat).
        
        - Sort pantry items by name, expiry date, and stock levels.
        
    * Recipe Discovery:
        - Search for recipes based on selected pantry ingredients.
        
        - Filter recipes by dietary preferences (e.g., vegan, gluten-free), diet intolerances, and recipe type (e.g., Chinese, dessert).
        
        - View recipe details and open them in a web browser for further instructions.
        
        - Favourite recipes to save them for easy access later.
        
        
    * Favourites:
    
        * Save your favourite recipes for quick access.
        
    * User-Orientated Interface:
    
        * Clean and modern UI designed with MVVM architecture.
        
        * Colour psychology applied in the UI to enhance user experience.
        
         
## Tech Stack

    * Android (Java): The application is built using native Android with Java.
    
    * Spring Boot (Postgres API): The backend API is developed using Spring Boot and hosted on AWS Elastic Beanstalk.
    
    * Spoonacular API: For fetching data about ingredients and recipes. Visit Spoonacular for more information.
    
    * AWS Elastic Beanstalk: The backend API is deployed and hosted here for online availability.
    
    
## Architecture
Stock'n'Roll follows the MVVM (Model-View-ViewModel) architecture pattern to ensure a clear separation of concerns, making the app easy to maintain and scale.

## Screenshots
(Include screenshots here)

## Target Audience
This app is ideal for:

    * Home cooks looking for ways to make the most of their pantry.
    
    * Parents who need help organizing their kitchen and meal planning.
    
    * Young adults managing their pantry with a busy schedule.
    
    * Casual food enthusiasts who love trying out new recipes based on what they already have.

## Installation

    1. Clone the [repository](https://github.com/Candlelight-apps/stocknroll-frontend.git)    

    2. Open the project in Android Studio.
    
    3. Build and run the project on an Android device or emulator.

    
## API Setup

The backend API is deployed on AWS Elastic Beanstalk and can be accessed from the application directly. If you're interested in setting up your own version:

    1. Set up a PostgreSQL database.
    
    2. Configure the Spring Boot API (`https://github.com/Candlelight-apps/stocknroll-backend.git`) with your database credentials.
    ntend
    3. Deploy to AWS Elastic Beanstalk or any preferred hosting service.
    

## External Services

    * Spoonacular API: Used to retrieve information regarding ingredients and recipes. You can find more information here.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
