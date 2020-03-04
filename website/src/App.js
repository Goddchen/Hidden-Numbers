import React, { Component } from 'react'
import './App.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { List, ListItem, Card, CardContent, CardHeader, Box } from '@material-ui/core';
import Moment from 'react-moment'

class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
      magic: [],
    }
  }

  componentDidMount() {
    fetch(`http://localhost:8080/date/today`)
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
            <Moment format="L" />
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
                      <p>{this.magicMeta(m)}</p>
                    </CardContent>
                  </Card>
                </Box>
              </ListItem>)
        }
      </List>
    </div>
    );
  }

  magicName(magic) {
    if (magic.name === "equal") return "Equal number"
    if (magic.name === "odd") return "Odd number"
    if (magic.name === "prime") return "Prime number"
    return magic.name
  }

  magicMeta(magic) {
    return Object.keys(magic.args).map(key => <p>{this.magicMetaKey(key)}: {magic.args[key]}</p>)
  }

  magicMetaKey(key) {
    if (key === "digitSum") return "Digit Sum"
    if (key === "dayOfYear") return "Day of the year"
    if (key === "remainingDaysOfYear") return "Remaining days of year"
    return key
  }

  magicDescription(magic) {
    if (magic.name === "equal") return "This is an equal number"
    if (magic.name === "odd") return "This is an odd number"
    if (magic.name === "prime") return "This is a prime number"
    return magic.name
  }
}

export default App;
