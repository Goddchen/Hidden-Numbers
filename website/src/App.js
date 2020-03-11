import React, { Component } from 'react'
import './App.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { List, ListItem, Card, CardContent, CardHeader, Box, Link } from '@material-ui/core';
import Moment from 'react-moment'
import moment from 'moment'
import 'moment/min/locales'
import SVG from 'react-inlinesvg'
import './localization'
import { withTranslation } from 'react-i18next';

Moment.globalLocale = window.navigator.userLanguage || window.navigator.language
moment.globalLocale = window.navigator.userLanguage || window.navigator.language

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
      apiPath = "date/" + moment().format("YYYY-MM-DD")
    }
    // fetch("http://localhost:8080/api/" + apiPath)
    fetch("/api/" + apiPath)
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
    return (
      <div className="App">
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6">
              {this.title()}
            </Typography>
          </Toolbar>
        </AppBar>
        <a href="https://github.com/Goddchen/Hidden-Numbers/" target="_blank" rel="noopener noreferrer">
          <SVG src='<svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 250 250" fill="#ffffff" style="position: absolute; top: 0; right: 0"> \
                      <path d="M0 0l115 115h15l12 27 108 108V0z" fill="#000000"/> \
                      <path class="octo-arm" d="M128 109c-15-9-9-19-9-19 3-7 2-11 2-11-1-7 3-2 3-2 4 5 2 11 2 11-3 10 5 15 9 16" style="-webkit-transform-origin: 130px 106px; transform-origin: 130px 106px"/> \
                      <path class="octo-body" d="M115 115s4 2 5 0l14-14c3-2 6-3 8-3-8-11-15-24 2-41 5-5 10-7 16-7 1-2 3-7 12-11 0 0 5 3 7 16 4 2 8 5 12 9s7 8 9 12c14 3 17 7 17 7-4 8-9 11-11 11 0 6-2 11-7 16-16 16-30 10-41 2 0 3-1 7-5 11l-12 11c-1 1 1 5 1 5z"/> \
                    </svg>' />
        </a>
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
        <p>Version: ##VERSION##</p>
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
    const { t } = this.props;
    if (magic.name === "equal") return t("seeker.equal.name", magic.name)
    if (magic.name === "odd") return t("seeker.odd.name", magic.name)
    if (magic.name === "prime") return t("seeker.prime.name", magic.name)
    if (magic.name === "unlucky-number") return t("seeker.unlucky-number.name", magic.name)
    if (magic.name === "friday-13th") return t("seeker.friday-13th.name", magic.name)
    return magic.name
  }

  magicMeta(magic) {
    return Object.keys(magic.args)
      .reverse()
      .map((key, index) => <p key={index}>{this.magicMetaKey(key)}: {magic.args[key]}</p>)
  }

  magicMetaKey(key) {
    const { t } = this.props;
    if (key === "digitSum") return t("seeker.meta.digitsum", key)
    if (key === "dayOfYear") return t("seeker.meta.dayofyear", key)
    if (key === "remainingDaysOfYear") return t("seeker.meta.remainingdaysofyear", key)
    if (key === "origin") return t("seeker.meta.origin", key)
    if (key === "reason") return t("seeker.meta.reason", key)
    if (key === "dayOfMonth") return t("seeker.meta.dayofmonth", key)
    return key
  }

  magicDescription(magic) {
    const { t } = this.props;
    if (magic.name === "equal") return t("seeker.equal.description", magic.name)
    if (magic.name === "odd") return t("seeker.odd.description", magic.name)
    if (magic.name === "prime") return t("seeker.prime.description", magic.name)
    if (magic.name === "unlucky-number") return t("seeker.unlucky-number.description", magic.name)
    if (magic.name === "friday-13th") return (
      <span>{t("seeker.friday-13th.description", magic.name)} (<Link href="https://en.wikipedia.org/wiki/Friday_the_13th" target="_blank" rel="noopener noreferrer">Wikipedia</Link>)</span>
    )
    return magic.name
  }
}

export default withTranslation()(App);
