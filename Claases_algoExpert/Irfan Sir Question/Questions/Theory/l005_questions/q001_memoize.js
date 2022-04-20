// Create a memoize function that remembers previous inputs and stores them in cache so that itwon’t have to compute the same inputs more than once. 
// The function will take an unspecifiednumber of integer inputs and a reducer method

// Constraints: 1.The memozie function should be written from scratch.
// 2.3rd party libraries such as loaddash or underscore should not have been used.
// 3.The function should carry a name which should denote the functionality of it.
// 4.The function should be able to take n number of arguments


// Given Reduce Method:
function add(a, b) {
    return a + b
}

const memoize = (cb) => {
    let cache = {};
    return (...args) => {
        let cacheKey = args.map((val) => val.toString() + "+").join('');
        if (cacheKey in cache)
            return cache[cacheKey];
        else {
            let result = args.reduce((acc, val) => {
                return cb(acc, val);
            }, 0);
            cache[cacheKey] = result;
            return result;
        }
    }
};

const memoizeAdd = memoize(add);

//then calling...

console.log(memoizeAdd(100, 100));//return 200;
console.log(memoizeAdd(100));//return 100;
console.log(memoizeAdd(100, 200));//return 300;
console.log(memoizeAdd(100, 100));//return 200 without computing;
console.log(memoizeAdd(100, 100,200,300,400,500));//return 200;