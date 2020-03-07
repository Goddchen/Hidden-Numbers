import React, { Component } from 'react'
import './App.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { List, ListItem, Card, CardContent, CardHeader, Box, Link } from '@material-ui/core';
import Moment from 'react-moment'
import 'moment/min/locales'

Moment.globalLocale = window.navigator.userLanguage || window.navigator.language

class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
      magic: [],
    }
  }

  componentDidMount() {
    var urlSearchParams = new URLSearchParams(window.location.search)
    var path = window.location.pathname
    var apiPath;
    if (path.match("/date/*")) {
      apiPath = "date/" + path.split("/").pop()
    } else if (path.match("/number/*")) {
      apiPath = "number/" + path.split("/").pop()
    } else if (urlSearchParams.has("date")) {
      apiPath = "date/" + urlSearchParams.get("date")
    } else if (urlSearchParams.has("number")) {
      apiPath = "number/" + urlSearchParams.get("number")
    } else {
      apiPath = "date/today"
    }
    fetch("http://localhost:8080/api/" + apiPath)
      // fetch("/api/" + apiPath)
      .then(res => res.json())
      .then(data => {
        this.setState({
          magic: data
        })
      })
      .catch((err) => {
        console.error(err)
      })
  }

  render() {
    return (<div className="App">
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">
            {this.title()}
          </Typography>
        </Toolbar>
      </AppBar>
      <List>
        {
          this.state.magic
            .filter(m => m.name !== "equal")
            .filter(m => m.name !== "odd")
            .map((m, index) =>
              <ListItem key={index}>
                <Box width={1}>
                  <Card>
                    <CardHeader title={this.magicName(m)} />
                    <CardContent>
                      <p>{this.magicDescription(m)}</p>
                      {this.magicMeta(m)}
                    </CardContent>
                  </Card>
                </Box>
              </ListItem>)
        }
      </List>
    </div>
    );
  }

  title() {
    var urlSearchParams = new URLSearchParams(window.location.search)
    var path = window.location.pathname
    if (path.match("/date/*")) {
      return <Moment format="L" date={path.split("/").pop()} />
    } else if (path.match("/number/*")) {
      return path.split("/").pop()
    } else if (urlSearchParams.has("date")) {
      return <Moment format="L" date={urlSearchParams.get("date")} />
    } else if (urlSearchParams.has("number")) {
      return urlSearchParams.get("number")
    } else {
      return <Moment format="L" />
    }
  }

  magicName(magic) {
    if (magic.name === "equal") return "Equal number"
    if (magic.name === "odd") return "Odd number"
    if (magic.name === "prime") return "Prime number"
    if (magic.name === "unlucky-number") return "Unlucky number"
    if (magic.name === "friday-13th") return "Friday 13th"
    return magic.name
  }

  magicMeta(magic) {
    return Object.keys(magic.args).map((key, index) => <p key={index}>{this.magicMetaKey(key)}: {magic.args[key]}</p>)
  }

  magicMetaKey(key) {
    if (key === "digitSum") return "Digit Sum"
    if (key === "dayOfYear") return "Day of the year"
    if (key === "remainingDaysOfYear") return "Remaining days of year"
    if (key === "origin") return "Origin"
    if (key === "reason") return "Reason"
    return key
  }

  magicDescription(magic) {
    if (magic.name === "equal") return "This is an equal number"
    if (magic.name === "odd") return "This is an odd number"
    if (magic.name === "prime") return "This is a prime number"
    if (magic.name === "unlucky-number") return "This is an unlucky number in parts of the world"
    if (magic.name === "friday-13th") return (
      <span>Friday 13th. See <Link href="https://en.wikipedia.org/wiki/Friday_the_13th" target="_blank" rel="noopener noreferrer">Wikipedia</Link></span>
    )
    return magic.name
  }
}

export default App;
