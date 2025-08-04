let magicNumber: number = 5;
magicNumber = 10;
//magicNumber = "20";

interface Person {
    name: string,
    age: number,
    speak: () => void
}

// type Animal {
//     name: String,
//     age: number
// }

const human: Person = {
    name: "Jessica",
    age: 27,
    speak: () => {
        console.log("Good Morning")
    }
}

human.speak();

console.log(human.name)
