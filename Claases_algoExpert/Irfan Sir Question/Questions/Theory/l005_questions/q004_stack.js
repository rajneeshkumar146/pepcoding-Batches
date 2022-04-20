// Refactor the below stack implementation, using the concept of closure, such that there is noway to access items array outside of createStack() function scope:

// function createStack() {
//     return {
//         items: [],
//         push(item) {
//             this.items.push(item);
//         },
//         pop() {
//             return this.items.pop();
//         }

//     };
// }

// const stack = createStack();
// stack.push(10);
// stack.push(5);
// stack.pop(); //=>5
// console.log(stack.items); // =>[10]
// stack.items = [10, 100, 1000]; //Encapsulation broken!
// console.log(stack.items)

function createStack() {
    const items = [];
    return {
        push(item) {
            items.push(item);
        },

        pop() {
            return items.pop();
        }

    };
}

const stack = createStack();
stack.push(10);
stack.push(5);
stack.pop(); //=>5
console.log(stack.items); // undefine
stack.items = [10, 100, 1000]; //it should not allowed!
console.log(stack.items)
console.log(stack.pop()); //=>10
console.log(stack.items.pop()); // undefine

// ========================================================================