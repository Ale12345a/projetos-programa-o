
// Constructor function
function Image(title, artist, date) {
    this.title = title;
    this.artist = artist;
    this.date = date;
}

// Images object
let images = {
    list: [],

    // Check if an image already exists
    contains: function(title) {
        return this.list.some(image => image.title === title);
    },

    // Add a new image if it does not already exist
    add: function(title, artist, date) {
        if (!this.contains(title)) {
            let image = new Image(title, artist, date);
            this.list.push(image);
        }
    },

    // Display all images
    show: function() {
        this.list.forEach(image => {
            console.log(`${image.title} (${image.artist}, ${image.date})`);
        });
    },

    // Remove all images
    clear: function() {
        this.list = [];
    }
};

// Test the script
images.add('Mona Lisa', 'Leonardo da Vinci', 1503);
images.add('The Last Supper', 'Leonardo da Vinci', 1495);
images.add('The Starry Night', 'Vincent van Gogh', 1889);
images.add('Mona Lisa', 'Leonardo da Vinci', 1503);

images.show();

images.clear();

images.show();
