// 1. What is the output of the below problem and why:

function createIncrement() {
    let count = 0;

    function increment() {
        count++;
        console.log(count);
    }

    let message = `Count is ${count}`;
    function log() {
        console.log(message);
    }

    return [increment, log]

}

const [increment, log] = createIncrement();
increment();
increment();
increment();

log(); //what is logged?
