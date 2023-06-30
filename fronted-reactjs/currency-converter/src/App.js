import React, { useState, useEffect  } from "react";
import "./App.css";

const API_KEY = "874821593b90a05832e67b8e"; // Replace with your own API key


function App() {
  const [currencyOptions, setCurrencyOptions] = useState([]);
	const [fromCurrency, setFromCurrency] = useState("");
	const [toCurrency, setToCurrency] = useState("");
	const [amount, setAmount] = useState("");
	const [convertedAmount, setConvertedAmount] = useState("");
  const [conversionRate, setConversionRate] = useState("");

	const convertCurrency = async () => {
		try {
			console.log(fromCurrency + " " + toCurrency + " " + amount);
			const response = await fetch(
				`http://localhost:8080/exchange/${fromCurrency}/${toCurrency}/${amount}`
			);
			console.log(response);
			var data = await response.json();
			if (data) {
				const convertedAmount = data.amount.toFixed(2);
				setConvertedAmount(convertedAmount);
        setConversionRate(data.conversion_rate);
			} else {
				console.log(data.error);
				setConvertedAmount("");
			}
		} catch (error) {
			console.log(error);
			setConvertedAmount("");
		}
	};

	const fetchCurrencyOptions = async () => {
		try {
			const response = await fetch("http://localhost:8080/exchange/currency");
			const data = await response.json();
      console.log(data);
				setCurrencyOptions(data);

		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		fetchCurrencyOptions();
	}, []);
	return (
		<div className="App">
			<div className="container">
				<h1>Currency Converter</h1>
				<div className="form-group">
					<label>Amount:</label>
					<div className="input-group">
						<input
							type="number"
							value={amount}
							onChange={(e) => setAmount(e.target.value)}
							placeholder="Enter amount"
						/>
					</div>
				</div>
				<div className="form-group">
					<label>From:</label>
					<div className="input-group select-group">
						<select
							value={fromCurrency}
							onChange={(e) => setFromCurrency(e.target.value)}
						>
							<option value="">Select currency</option>
							{currencyOptions.map((currency) => (
								<option
									key={currency.currencyShortcut}
									value={currency.currencyShortcut}
								>
									{currency.currencyName} ({currency.currencyShortcut})
								</option>
							))}
						</select>
					</div>
				</div>
				<div className="form-group">
					<label>To:</label>
					<div className="input-group select-group">
						<select
							value={toCurrency}
							onChange={(e) => setToCurrency(e.target.value)}
						>
							<option value="">Select currency</option>
							{currencyOptions.map((currency) => (
								<option
									key={currency.currencyShortcut}
									value={currency.currencyShortcut}
								>
									{currency.currencyName} ({currency.currencyShortcut})
								</option>
							))}
						</select>
					</div>
				</div>
				<button className="convert-button" onClick={convertCurrency}>
					Convert
				</button>
				{convertedAmount && (
					<div className="result-label">
            <div>
						{amount} {fromCurrency} = {convertedAmount} {toCurrency}
            </div>
            <div className="conversion-rate">
      Conversion Rate: 1 {fromCurrency} = {conversionRate.toFixed(4)} {toCurrency}
    </div>
					</div>
				)}
			</div>
		</div>
	);
}

export default App;
