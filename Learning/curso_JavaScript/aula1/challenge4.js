
// Constructor function (same constructor as before)
function Image(title, artist, date) {
    this.title = title;
    this.artist = artist;
    this.date = date;
}

// Add show() to the Image prototype
Image.prototype.show = function() {
    console.log(`${this.title} (${this.artist}, ${this.date})`);
};

// Images object
let images = {
    list: [],

    contains: function(title) {
        return this.list.some(image => image.title === title);
    },

    add: function(title, artist, date) {
        if (!this.contains(title)) {
            this.list.push(new Image(title, artist, date));
        }
    },

    show: function() {
        this.list.forEach(image => image.show());
    },

    clear: function() {
        this.list = [];
    }
};

// Add edit() without rewriting the object
images.edit = function(title, artist, date) {
    let image = this.list.find(image => image.title === title);

    if (image) {
        image.artist = artist;
        image.date = date;
    }
};

// Add delete() without rewriting the object
images.delete = function(title) {
    let index = this.list.findIndex(image => image.title === title);

    if (index !== -1) {
        this.list.splice(index, 1);
    }
};

// Test the script
images.add('Mona Lisa', 'Leonardo da Vinci', 1503);
images.add('The Last Supper', 'Leonardo da Vinci', 1495);
images.add('The Starry Night', 'Vincent van Gogh', 1889);

images.edit('Mona Lisa', 'Leonardo da Vinci', 1504);
images.delete('The Last Supper');

images.show();
