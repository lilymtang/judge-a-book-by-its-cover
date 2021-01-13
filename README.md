# Judge a Book by its Cover 

We've always been told to not judge a book by its cover, so I decided to make an app that encourages doing the complete opposite. Looking at book covers is kind of reminiscent of the days of browsing an actual library. The cover is the first thing you see when you pull a book from the shelf and can range from artistic to minimalist to thought-provoking. This app allows you to browse the most recent New York Times Best Seller lists via a gallery of book covers. 

<img src="home.png" height="600" >


## Requirements

### Activities

- Main activity
  - This activity is the entry point of the app. It displays a scrolling gallery of covers of this week's bestsellers using a recycler view and grid layout. 
  - The tab layout has 2 tabs: one is the Library, where the user can browse all the best sellers; the second is My Shelf, where users can see their saved books. Users can either click or swipe between the tabs. 
  - The top app bar collapses when scrolling while the tabs remain in view for clear user navigation. 
- Settings activity
  - This activity is accessed from the 3-dot overflow menu on the Main Activity. The only functionality of this page currently is to export a user's shelf.
 
<img src="settings.png" height="600" >

### Fragments

- Home and Shelf fragments
  - Both tabs are implemented using fragments in order to take advantage of tab layout and ViewPager. Because Library and Shelf have parallel layouts, using fragments also allowed for code reusability and a shared app bar. 
- Detail fragment
  - This fragment is a BottomSheetDialog that displays information about each book and 2 actions: adding/removing books to My Shelf, and viewing the book's Amazon listing. 
  - It is used to display details for books in both the Library and My Shelf tabs. 
  - Both Add and Remove actions are immediately reflected in My Shelf.
  
<img src="add.png" height="600" >

### Service

- Export service
  - Within Settings, the Export button launches a service that queries the Room repository and exports to CSV all the books in My Shelf. This operation does not take long, but running it in as a service ensures that the export will complete even if the user leaves the app. 

### MVP

- Views - Fragments and Activities. These maintain references to the Presenter classes and notify presenters when there is new user input. 
- Presenters - Presenters update the model and implement callbacks to the view so that the views know when they need to update. 
- Contracts - These are interfaces that I use to establish the View - Presenter relationship. BaseContract defines basic methods that every View and Presenter implements, and there are additional View specific contracts for each relationship. 
- Model - I use the repository pattern to create an abstraction layer for data access from multiple data sources: local (Room) and remote (NYTApi). Database objects (I called mine Books) are defined here.

### REST API

- This app uses the NYT Books API https://developer.nytimes.com/docs/books-product/1/overview to get the Best Sellers List for the most recent week. 
- I used Retrofit2 and Moshi to asychronously send the server request, process the response, and notify callbacks to ensure non-blocking UI performance. 

### Material Design Components

- App bar is a collapsing toolbar
- Tabs to easily navigate between Library and My Shelf
- Book details implemented using a bottom sheet dialog
- Snackbars to provide feedback to the user upon actions like adding/removing books 
- Export button in Settings

### Data Persistence

- I used Room to persist the books that a user has saved to My Shelf. 
- Calls to Room are made from nonblocking coroutines.
