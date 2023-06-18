# OIBSIP






















<!DOCTYPE html>
<html>
<head>
    <title>Simple Calculator</title>
    <style>
        .container {
            max-width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="text"] {
            width: 90%;
            margin-bottom: 10px;
            padding: 10px;
        }
        input[type="button"] {
            width: 50px;
            height: 50px;
            margin-right: 5px;
        }
        #result {
            font-weight: bold;
            text-align: right;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <h1 align="center">Simple Calculator</h1>
    <div class="container">
        <input type="text" id="result" readonly>
        <input type="button" value="1" class="num-button">
        <input type="button" value="2" class="num-button">
        <input type="button" value="3" class="num-button">
        <br>
        <input type="button" value="4" class="num-button">
        <input type="button" value="5" class="num-button">
        <input type="button" value="6" class="num-button">
        <br>
        <input type="button" value="7" class="num-button">
        <input type="button" value="8" class="num-button">
        <input type="button" value="9" class="num-button">
        <br>
        <input type="button" value="0" class="num-button">
        <input type="button" value="+" class="operator-button">
        <input type="button" value="-" class="operator-button">
        <br>
        <input type="button" value="*" class="operator-button">
        <input type="button" value="/" class="operator-button">
        <input type="button" value="=" id="equal-button">
        <br>
        <input type="button" value="Clear" id="clear-button">
    </div>

    <script>
        // Get all the number buttons
        const numButtons = document.querySelectorAll('.num-button');
        numButtons.forEach(button => {
            button.addEventListener('click', () => {
                const value = button.value;
                appendToResult(value);
            });
        });

        // Get all the operator buttons
        const operatorButtons = document.querySelectorAll('.operator-button');
        operatorButtons.forEach(button => {
            button.addEventListener('click', () => {
                const value = button.value;
                appendToResult(value);
            });
        });

        // Get the equal button
        const equalButton = document.getElementById('equal-button');
        equalButton.addEventListener('click', calculateResult);

        // Get the clear button
        const clearButton = document.getElementById('clear-button');
        clearButton.addEventListener('click', clearResult);

        function appendToResult(value) {
            document.getElementById('result').value += value;
        }

        function calculateResult() {
            const expression = document.getElementById('result').value;
            try {
                const result = eval(expression);
                document.getElementById('result').value = result;
            } catch (error) {
                document.getElementById('result').value = 'Error';
            }
        }

        function clearResult() {
            document.getElementById('result').value = '';
        }
    </script>
</body>
</html>
