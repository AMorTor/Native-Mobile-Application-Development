const form = document.getElementById("signup")

form.addEventListener("submit", async (e) => {
    e.preventDefault()

    const {email, password} = e.target.elements

    console.log({email, password})
})